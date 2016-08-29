package ru.sbt.les15_Sockets.Second;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server1 {
    private static final int PORT = 1243;
    public static void main(String[] args) {
        try {
            System.out.println("Started");
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                try (Socket accepted = serverSocket.accept()) {
                    System.out.println("Accepted connection from " + accepted.getLocalAddress());
                    OutputStream outputStream = accepted.getOutputStream();

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

                    writer.write(new Date().toString() + "\n");
                    writer.flush();
                    Thread.sleep(10);
                    writer.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
