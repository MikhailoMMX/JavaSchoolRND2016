package ru.sbt.les9_Serialization.HomeWork;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {
    private static final int PORT = 12345;
    private static final String SEND = "send";
    private static final String RECEIVE = "receive";
    private static final String EXIT = "exit";
    private static final String RECEIVED_FOLDER = "Inbox\\";


    private static void ReceiveFiles(String Login) {
        String host = null;
        try (Socket socket = new Socket(host, PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());){
            oos.writeObject(RECEIVE);
            oos.writeObject(Login);
            if (!Files.exists(Paths.get(RECEIVED_FOLDER)))
                Files.createDirectory(Paths.get(RECEIVED_FOLDER));
            try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());){
                int number = ois.readInt();
                for (int i = 0; i< number; ++i){
                    Message m = (Message) ois.readObject();
                    m.WriteToFile(RECEIVED_FOLDER+m.getFileName());
                    System.out.println("Received file \""+m.getFileName()+"\" from \""+m.From+"\"");
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void SendFile(BufferedReader br, String command, String login) throws IOException {
        System.out.print("Recipient: ");
        String To = br.readLine();
        System.out.print("File: ");
        String filename = br.readLine();

        Message mes = new Message(login, To, filename);
        String host = null;
        try (Socket socket = new Socket(host, PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());){
            oos.writeObject(SEND);
            oos.writeObject(mes);
            System.out.println("File sent");
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Login: ");
        String login = "";
        try {
            login = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Commands:");
        System.out.println("  "+SEND);
        System.out.println("  "+RECEIVE);
        System.out.println("  "+EXIT);

        String command = EXIT;
        do {
            try {
                System.out.print(">");
                command = br.readLine();

                if (command.equals(SEND))
                    SendFile(br, command, login);
                else if (command.equals(RECEIVE))
                    ReceiveFiles(login);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        } while (!command.equals(EXIT));
    }
}
