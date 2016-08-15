package ru.sbt.les11_MultiThreading;

public class SomeTask implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i<4; ++i) {
            System.out.println("Current Thread: " + Thread.currentThread().getName() + "(" + i + ")");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("");
        }
    }
}
