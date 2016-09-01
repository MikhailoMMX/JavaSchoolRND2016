package ru.sbt.les15_Sockets.Homework;

import ru.sbt.les9_Serialization.HomeWork.Message;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Listener implements Runnable{
    BufferedReader reader;

    public Listener(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        while (true){
            try {
                String line = reader.readLine();
                if (line == null)
                    return;
                System.out.println(line);
            } catch (IOException e) {
                return;
            }
        }
    }
}

public class Client {
    private static final int PORT = 1243;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(HOST, PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            )
        {
            System.out.print("Enter your name: ");
            String name = consoleReader.readLine();
            writer.write(name + "\n");
            writer.flush();

            String response = reader.readLine();
            if (!response.startsWith("Error: ")) {
                System.out.println(response);
                ExecutorService listener = Executors.newSingleThreadExecutor();
                Future listenerFuture = listener.submit(new Listener(reader));
                while (true) {
                    if (listenerFuture.isDone()) {
                        listener.shutdown();
                        break;
                    }
                    String newLine = consoleReader.readLine();
                    writer.write(newLine + "\n");
                    writer.flush();
                }
            }
            else
                System.out.println("Could not connect to the server: "+ response);
        } catch (IOException e) {
        }

    }
}
