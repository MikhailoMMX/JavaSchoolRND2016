package ru.sbt.les15_Sockets.Homework;

import java.io.*;
import java.net.Socket;

public class ClientInfo {
    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private String clientName;
    private volatile boolean isAlive = true;

    public Socket getSocket() {
        return socket;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getClientName() {
        return clientName;
    }

    public ClientInfo(Socket socket) throws IOException {
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        clientName = reader.readLine();
    }

    public void Disconnect() {
        try {
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readMessage(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            isAlive = false;
        }
        return "";
    }

    public void sendMessage(String s) {
        try {
            writer.write(s+"\n");
            writer.flush();
        } catch (IOException e) {
            isAlive = false;
        }

    }
}
