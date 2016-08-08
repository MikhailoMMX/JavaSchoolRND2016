package ru.sbt.les9_Serialization.HomeWork;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class DemoHW {
    interface MessageService {
        void send(Message message);

        Message receive();
    }

    static class Message implements Serializable {
        long id;
        String user;
        String message;

        Message(String user, String message) {
            this.id = System.currentTimeMillis();
            this.user = user;
            this.message = message;
        }

        String getKey() {
            return "" + System.identityHashCode(Thread.currentThread()) + user + id;
        }
    }

    static class Client {
        MessageService messageService;

        Client(MessageService messageService) {
            this.messageService = messageService;
        }

        void start() {
            Console console = System.console();
            if (console == null) {
                throw new IllegalStateException("Can't use console");
            }
            System.out.print("Enter your username: ");
            String username = console.readLine();

            String line;
            System.out.print("Enter message:");
            while ((line = console.readLine()) != null) {
                Message message = new Message(username, line);
                messageService.send(message);
                System.out.print("Enter message:");
            }
            System.out.println("End client!");
        }
    }

    static class Server {
        String lastReceivedMessageKey;

        MessageService messageService;


        Server(MessageService messageService) {
            this.messageService = messageService;
        }

        void start() {
            while (true) {
                System.out.println("Waiting for message...");
                Message receivedMessage = messageService.receive();
                if (!receivedMessage.getKey().equals(lastReceivedMessageKey)) {
                    lastReceivedMessageKey = receivedMessage.getKey();
                    System.out.println("new message: " + receivedMessage.id);
                    System.out.println("       from: " + receivedMessage.user);
                    System.out.println("       body: " + receivedMessage.message);
                }
            }
        }
    }

    private static void showUsage() {
        System.err.println("Use first argument to identify app mode: client | server");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            showUsage();
            return;
        }
        MessageService messageService = new MessageServiceThroughFileSerialization("chat-message.ser");
        switch (args[0]) {
            case "client":
                new Client(messageService).start();
                break;
            case "server":
                new Server(messageService).start();
                break;
            default:
                showUsage();
        }
    }

    private static class MessageServiceThroughFileSerialization implements MessageService {

        private File messageFile;

        public MessageServiceThroughFileSerialization(String messageFile) {
            this.messageFile = new File(messageFile);
        }

        @Override
        public void send(Message message) {
            try {
                SerializableUtils.serialize(messageFile.getAbsolutePath(), message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Message receive() {
            while (true) {
                if (messageFile.exists() && messageFile.lastModified() < System.currentTimeMillis() - 500L) {
                    try {
                        Message message = (Message) SerializableUtils.deserialize(messageFile.getAbsolutePath());
                        messageFile.delete();
                        return message;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
