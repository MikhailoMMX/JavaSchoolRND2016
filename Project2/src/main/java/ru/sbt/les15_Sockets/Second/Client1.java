package ru.sbt.les15_Sockets.Second;

import java.io.*;
import java.net.Socket;

public class Client1 {
    private static final int PORT = 1243;
    private static final String HOST = "localhost";
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(HOST, PORT);
        OutputStream outputStream = clientSocket.getOutputStream();
        InputStream inputStream = clientSocket.getInputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        writer.write("Hello\n");
        writer.flush();
        String s = reader.readLine();
        System.out.println(s);
        reader.close();
        writer.close();
        clientSocket.close();
    }
}
