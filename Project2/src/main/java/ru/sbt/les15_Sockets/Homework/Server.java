package ru.sbt.les15_Sockets.Homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server {
    private static final int LIMIT = 3;
    private static final int PORT = 1243;

    private static HashMap<Socket, ClientInfo> connectedClients = new HashMap<>();
    private static ReentrantReadWriteLock clientsLock = new ReentrantReadWriteLock();

    private static ClientInfo addClient(Socket s) throws IOException {
        ClientInfo client = new ClientInfo(s);
        clientsLock.writeLock().lock();
        connectedClients.put(s, client);
        sendToAll("User \""+connectedClients.get(s).getClientName()+"\" has joined the chat");
        clientsLock.writeLock().unlock();
        return client;
    }

    static void removeClient(Socket s){
        String name = connectedClients.get(s).getClientName();
        clientsLock.writeLock().lock();
        connectedClients.remove(s);
        sendToAll("User \""+name+"\" has left the chat");
        clientsLock.writeLock().unlock();
        System.out.println("Client " + name + " disconnected");
    }

    static void sendToAll(String s){
        clientsLock.writeLock().lock();
        for (Socket socket : connectedClients.keySet()){
            ClientInfo clientInfo = connectedClients.get(socket);
            clientInfo.sendMessage(s);
        }
        clientsLock.writeLock().unlock();
    }

    private static void reject(Socket socket){
        try {
            ClientInfo clientInfo = new ClientInfo(socket);
            clientInfo.sendMessage("Error: Server is overloaded");
            clientInfo.Disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Server started, limit = " + LIMIT + " connections");

        ExecutorService pool = Executors.newFixedThreadPool(LIMIT);
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true){
            try{
                Socket socket = serverSocket.accept();

                System.out.print("Accepted connection from " + socket.getInetAddress());
                if (connectedClients.size()>= LIMIT){
                    reject(socket);
                    System.out.println("; connection rejected: limit is reached");
                    continue;
                }
                ClientInfo clientInfo = addClient(socket);
                Worker worker = new Worker(clientInfo);
                pool.submit(worker);
                System.out.println("; client " + clientInfo.getClientName() + " connected");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
