package ru.sbt.les15_Sockets.Third;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

class Worker implements Runnable{
    Socket socket;
    String Prefix;
    public Worker(Socket socket) {
        this.socket = socket;
        Prefix = new Long(Thread.currentThread().getId()).toString() + ": ";
    }

    private static final int MAX = 100;
    @Override
    public void run() {

        int number = ThreadLocalRandom.current().nextInt(MAX)+1;
        try (InputStream inputStream = socket.getInputStream(); OutputStream outputStream = socket.getOutputStream();){
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            writer.write("Угадай число (от 1 до "+MAX+")\n");
            writer.flush();
            System.out.println(Prefix + "Game started with " + number);
            String reply;
            while (true) {
                int r=0;
                if ((reply = reader.readLine()) != null) {
                    r = Integer.parseInt(reply);
                    System.out.print(Prefix + "Received " + reply + "; ");
                    if (r>number) {
                        writer.write("Меньше\n");
                        System.out.println("sent <");
                    }
                    else if(r<number) {
                        writer.write("Больше\n");
                        System.out.println(Prefix + "Sent >");
                    }
                    else
                        break;
                }
                writer.flush();
            }
            System.out.println(Prefix + "Game finished");
            writer.write("Правильно\n");
            writer.flush();
            Thread.sleep(10);
            socket.close();
        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Server1 {
    private static final int PORT = 1243;
    public static void main(String[] args) {
        try {
            System.out.println("Started");
            ServerSocket serverSocket = new ServerSocket(PORT);
            ExecutorService pool = Executors.newFixedThreadPool(10);
            while (true) {
                Socket accepted = serverSocket.accept();
                System.out.println("Accepted connection from " + accepted.getLocalAddress());
                pool.submit(new Worker(accepted));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
