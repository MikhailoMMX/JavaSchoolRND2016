package ru.sbt.les15_Sockets.First;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    private static final int PORT = 1243;
    public static void main(String[] args) {
        try {
            System.out.println("Started");
            ServerSocket serverSocket = new ServerSocket(PORT);
            try (Socket accepted = serverSocket.accept()){
                System.out.println("Accepted connection from " + accepted.getLocalAddress());
                InputStream inputStream = accepted.getInputStream();
                OutputStream outputStream = accepted.getOutputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

                String s = reader.readLine();
                System.out.println("Received: " + s);

                System.out.println("Sending reply");
                writer.write("Ok\n");
                reader.close();
                writer.close();
                accepted.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
