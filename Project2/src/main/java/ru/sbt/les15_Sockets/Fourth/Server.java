package ru.sbt.les15_Sockets.Fourth;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    private static final int PORT = 1243;
    private static final int BUFFERSIZE = 256;
    public static void main(String[] args) throws IOException {
        try (DatagramSocket socket = new DatagramSocket(PORT)){
            byte[] buffer = new byte[BUFFERSIZE];
            String command = "";
            do{
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                socket.receive(dp);
                command = new String(dp.getData(), 0, dp.getLength());
                System.out.println(command);
            }while (!command.equals("exit"));
        }
    }
}
