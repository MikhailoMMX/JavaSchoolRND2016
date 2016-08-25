package ru.sbt.les13_MultiThreading;

public class MyRunnable implements Runnable {
    private volatile boolean cancelling = false;
    private volatile boolean started = false;
    private Runnable innerRunnable;

    public MyRunnable(Runnable innerRunnable) {
        this.innerRunnable = innerRunnable;
    }

    public void cancel() {
        cancelling = true;
    }

    public boolean isCancelled(){
        return cancelling && !started;
    }

    @Override
    public void run() {
        if (!cancelling) {
            started = true;
            innerRunnable.run();
        }
    }
}
