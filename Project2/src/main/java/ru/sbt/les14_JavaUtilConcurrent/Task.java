package ru.sbt.les14_JavaUtilConcurrent;

public class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("Task is running in \"" + Thread.currentThread().getName()+"\"");
    }
}
