package ru.sbt.les15_Sockets.Homework;

public class Worker implements Runnable{
    private ClientInfo info;

    public Worker(ClientInfo info) {
        this.info = info;
    }

    @Override
    public void run() {
        while (true){
            String Message = info.readMessage();
            if (Message.equals("") && !info.isAlive()){
                info.Disconnect();
                Server.removeClient(info.getSocket());
                return;
            }
            Server.sendToAll(info.getClientName() + ": " + Message);
            System.out.println("Sent message from " + info.getClientName());
        }
    }
}
