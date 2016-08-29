package ru.sbt.les15_Sockets.Fifth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private static final int PORT = 1243;
    private static final String HOST = "224.0.0.22";
    public static void main(String[] args) throws IOException {
        InetAddress group = InetAddress.getByName(HOST);
        try(
                DatagramSocket socket = new DatagramSocket();
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        )
        {
            String line;
            do{
                System.out.print("Enter message: ");
                line = reader.readLine();
                byte[] bytes = line.getBytes();
                DatagramPacket dp = new DatagramPacket(bytes, bytes.length, group, PORT);
                socket.send(dp);
            }while(!line.equals("exit"));
        }
    }
}

