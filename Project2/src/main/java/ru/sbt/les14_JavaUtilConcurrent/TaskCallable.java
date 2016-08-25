package ru.sbt.les14_JavaUtilConcurrent;

import java.util.concurrent.Callable;

public class TaskCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Callable: " + Thread.currentThread().getName());
        return Thread.currentThread().getName();
    }
}
