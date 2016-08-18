package ru.sbt.les12_MultiThreading;

public class MonitorExample {
    private String message;
    public void doMessage(){
        synchronized (this){
            try {
                while (message == null) {
                    wait();
                    System.out.println(message);
                    this.message = null;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void sendMessage(String message){
        synchronized (this){
            this.message = message;
            notify();
        }
    }
}
