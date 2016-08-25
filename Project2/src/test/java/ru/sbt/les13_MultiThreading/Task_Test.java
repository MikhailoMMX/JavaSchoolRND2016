package ru.sbt.les13_MultiThreading;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class Task_Test {
    @Test(expected = IllegalArgumentException.class)
    public void Test_IllegalArguments(){
        Task task = new Task(null);
    }

    private Callable<String> newCallable(){
        return new Callable<String>() {
            @Override
            public String call(){
                System.out.println("Some callable");
                return "42";
            }
        };
    }
    private Callable<String> newCallableWithException(){
        return new Callable<String>() {
            @Override
            public String call(){
                System.out.println("Some callable");
                throw new RuntimeException();
            }
        };
    }

    @Test
    public void Test_get_SingleThread_1(){
        Task<String> task = new Task<>(newCallable());
        Assert.assertEquals("42", task.get());
    }

    @Test(expected = RuntimeException.class)
    public void Test_get_SingleThread_2(){
        Task<String> task = new Task<>(newCallableWithException());
        Assert.assertEquals("42", task.get());
    }

    @Test
    public void Test_get_MultiThread_1() throws InterruptedException {
        Task<String> task = new Task<>(newCallable());

        List<String> list = Collections.synchronizedList(new ArrayList<>());

        final int ThreadCount = 10;
        Thread[] threads = new Thread[ThreadCount];
        for (int i = 0; i < ThreadCount; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(task.get());
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < ThreadCount; i++) {
            threads[i].join();
        }
        Assert.assertEquals(ThreadCount, list.size());
        for (int i = 0; i < ThreadCount; i++) {
            Assert.assertEquals("42", list.get(i));
        }
    }

    @Test
    public void Test_get_MultiThread_2() throws InterruptedException {
        Task<String> task = new Task<>(newCallableWithException());

        List<RuntimeException> list = Collections.synchronizedList(new ArrayList<>());

        final int ThreadCount = 10;
        Thread[] threads = new Thread[ThreadCount];
        for (int i = 0; i < ThreadCount; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        task.get();
                    }
                    catch (Exception e){
                        list.add(new RuntimeException(e));
                    }
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < ThreadCount; i++) {
            threads[i].join();
        }
        Assert.assertEquals(ThreadCount, list.size());
        for (int i = 0; i < ThreadCount; i++) {
            Assert.assertEquals(RuntimeException.class.getName(), list.get(i).getClass().getName());
        }
    }
}
