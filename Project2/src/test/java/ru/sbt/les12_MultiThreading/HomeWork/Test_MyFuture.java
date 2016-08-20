package ru.sbt.les12_MultiThreading.HomeWork;

import org.junit.Assert;
import org.junit.Test;

public class Test_MyFuture {
    @Test
    public void Test_1(){
        MyFuture future = new MyFuture();
        future.set(42);
        Assert.assertEquals(42, future.get());
    }

    @Test
    public void Test_2(){
        final MyFuture future = new MyFuture();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                future.set(42);
            }
        }).start();
        Assert.assertEquals(42, future.get());
    }

    @Test(expected = RuntimeException.class)
    public void Test_3(){
        final MyFuture future = new MyFuture();
        new Thread(new Runnable() {
            @Override
            public void run() {
                future.setException(new RuntimeException());
            }
        }).start();
        future.get();
    }
}
