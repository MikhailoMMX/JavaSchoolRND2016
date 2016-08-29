package ru.sbt.les15_Sockets.Third;

import javax.sound.midi.Soundbank;
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
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(reader.readLine()+ ": ");
        String answer = null;
        while (true){
            if((answer = consoleReader.readLine()) != null){
                writer.write(answer+"\n");
                writer.flush();
            }
            String reply = reader.readLine();
            System.out.print(reply);
            if (reply.startsWith("Правильно"))
                break;
            System.out.print(": ");
        }

        reader.close();
        writer.close();
        clientSocket.close();
    }
}
