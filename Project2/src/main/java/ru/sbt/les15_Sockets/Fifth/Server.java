package ru.sbt.les15_Sockets.Fifth;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server {
    private static final int PORT = 1243;
    private static final int BUFFERSIZE = 256;
    private static final String MULTICASTGROUP = "224.0.0.22";

    public static void main(String[] args) throws IOException {
        try (MulticastSocket socket = new MulticastSocket(PORT)){
            InetAddress multicastAddr = InetAddress.getByName(MULTICASTGROUP);
            socket.joinGroup(multicastAddr);
            byte[] buffer = new byte[BUFFERSIZE];
            String command = "";
            do{
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                socket.receive(dp);
                command = new String(dp.getData(), 0, dp.getLength());
                System.out.println(command);
            }while (!command.equals("exit"));
            socket.leaveGroup(multicastAddr);
        }
    }
}
