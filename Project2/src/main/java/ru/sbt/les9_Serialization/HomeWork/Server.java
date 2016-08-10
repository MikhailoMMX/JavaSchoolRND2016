package ru.sbt.les9_Serialization.HomeWork;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Hashtable;

public class Server {
    private static final int PORT = 12345;
    private static final String MES_FILE_NAME = "messages.dat";

    private static class Messages implements Serializable {
        final static long serialVersionUID = 0L;
        public Hashtable<String, HashSet<Message>> messages;

        public Messages() {
            messages = new Hashtable<>();
        }
    }

    private static Messages M;

    private static void saveDB(){
        try( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MES_FILE_NAME))){
            oos.writeObject(M);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processReceivedMessage(Message message) throws IOException {
        if (M.messages.containsKey(message.To)){
            M.messages.get(message.To).add(message);
        }
        else {
            HashSet<Message> msgs = new HashSet<>();
            msgs.add(message);
            M.messages.put(message.To,  msgs);
        }
        saveDB();
    }

    private static Object[] TakeMessages(String to){
        if (!M.messages.containsKey(to))
            return new Message[0];
        Object[] messages = M.messages.get(to).toArray();
        M.messages.remove(to);
        saveDB();
        return messages;
    }

    public static void main(String[] args) {
        try {
            String current = new File( "." ).getCanonicalPath();
            System.out.println("Current dir:"+current);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (Files.exists(Paths.get(MES_FILE_NAME))) {
                M = (Messages) SerializableUtils.deserialize(MES_FILE_NAME);
                System.out.println("DB read successfully, users: " + M.messages.size());
            }
            else {
                M = new Messages();
                System.out.println("DB rempty");
            }

            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started...");
            while (true){
                Socket socket = serverSocket.accept();
                System.out.print("Accepted connection");
                try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); ){
                    String command = (String) in.readObject();
                    System.out.println(" command \"" + command + "\"");
                    if (command.equals("send")) {
                        Message mes = (Message) in.readObject();
                        processReceivedMessage(mes);
                        System.out.println("Received message from \"" + mes.From + "\" to \"" + mes.To + "\" file \"" + mes.getFileName()+"\"");
                    }
                    else if (command.equals("receive")){
                        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
                            String From = (String) in.readObject();
                            Object[] msgs = TakeMessages(From);
                            oos.writeInt(msgs.length);
                            for (Object message : msgs) {
                                Message msg = (Message) message;
                                oos.writeObject(message);
                                System.out.println("Sent message from \"" + msg.From + "\" to \"" + msg.To + "\" file \"" + msg.getFileName()+"\"");
                            }
                        }
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
