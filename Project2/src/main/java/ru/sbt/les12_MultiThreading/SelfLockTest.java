package ru.sbt.les12_MultiThreading;

public class SelfLockTest {
    public synchronized void doSomething(){
        System.out.println("Synchronized method");
    }   //плохо


    private final Object lock = new Object();
    public void doSomethingDifferently(){
        synchronized (lock){
            System.out.println("Synchronized method");
        }
    }   //хорошо
}
