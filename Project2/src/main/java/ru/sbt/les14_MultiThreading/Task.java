package ru.sbt.les14_MultiThreading;

import java.util.concurrent.Callable;

public class Task <T>{
    private T result;
    private RuntimeException exception;
    private Callable<? extends T> callable;
    private final Object lock = new Object();

    public Task(Callable<? extends T> callable) {
        if (callable == null)
            throw new IllegalArgumentException();
        this.callable = callable;
    }

    public T get() {
        if (result == null && exception == null){
            synchronized (lock){
                if (result == null && exception == null){
                    try {
                        result = callable.call();
                    }
                    catch (RuntimeException re){
                        exception = re;
                    }
                    catch (Exception e){
                        exception = new RuntimeException(e);
                    }
                }
            }
        }
        if (exception != null)
            throw exception;
        return result;
    }

}
