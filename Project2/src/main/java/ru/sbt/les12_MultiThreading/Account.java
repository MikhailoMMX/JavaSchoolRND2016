package ru.sbt.les12_MultiThreading;

public class Account implements Runnable {
    private final Object myLock = new Object();  // так правильно

    private int saldo;

    public Account(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void decreaseSaldo(int sum){
        System.out.println("Try decrease saldo");

        synchronized (myLock) {
            if (saldo - sum < 0) {
                System.out.println("Overdraft not available");
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                saldo -= sum;
            }
        }
    }

    @Override
    public void run() {
        int x = 12;
        for (int i = 0; i < x; i++) {
            decreaseSaldo(10);
        }
        if (getSaldo()<0)
            throw new RuntimeException("Current account overdrafted");
    }
}
