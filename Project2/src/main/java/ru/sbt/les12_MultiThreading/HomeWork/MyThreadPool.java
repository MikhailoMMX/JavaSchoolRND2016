package ru.sbt.les12_MultiThreading.HomeWork;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Callable;

//пул потоков
//Позволяет принимать к исполнению задачи и возращает MyFuture для каждой принятой задачи.
//дает возможность менять количество потоков порождая новые или останавливая имеющиеся
//а также завершить все потоки

public class MyThreadPool {
    private final Object internalLock = new Object();
    private final Object tasksLock = new Object();

    private List<MyThread> threads;
    private Deque<Pair<Callable, MyFuture>> tasks;
    private int threadsCount;

    public MyThreadPool(int threadsCount) {
        threads = new ArrayList<>();
        tasks = new ArrayDeque<>();
        setThreadCount(threadsCount);
    }

    private void addThreads(int n){
        synchronized (internalLock) {
            for (int i = 0; i < n; i++) {
                MyWorker w = new MyWorker(this);
                MyThread t = new MyThread(w);
                threads.add(t);
                t.start();
            }
            threadsCount += n;
        }
    }

    private void stopThreads(int n){
        synchronized (internalLock) {
            for (int i = 0; i < n; i++) {
                threads.get(threads.size() - 1).interrupt();
                threads.remove(threads.size() - 1);
            }
            threadsCount -= n;
            synchronized (tasksLock){
                tasksLock.notifyAll();
            }
        }
    }

    public void setThreadCount(int n){
        if (n<0)
            throw new IllegalArgumentException("Thread number must be not less than 0");
        if (n == threadsCount)
            return;
        if (n>threadsCount)
            addThreads(n - threadsCount);
        else
            stopThreads(threadsCount - n);
    }

    public MyFuture submit(Callable c){
        if (c == null)
            throw new IllegalArgumentException("Argument null in ThreadPool.submit()");
        MyFuture f = new MyFuture();
        Pair<Callable, MyFuture> p = new Pair<>(c, f);
        synchronized (tasksLock) {
            tasks.add(p);
            tasksLock.notify();
        }
        return f;
    }

    protected Pair<Callable, MyFuture> nextTask(){
        synchronized (tasksLock){
            while (true) {
                try {
                    Pair<Callable, MyFuture> p = tasks.pollFirst();
                    if (p == null) {
                        tasksLock.wait();
                        return tasks.pollFirst();
                    }
                    else
                        return p;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
