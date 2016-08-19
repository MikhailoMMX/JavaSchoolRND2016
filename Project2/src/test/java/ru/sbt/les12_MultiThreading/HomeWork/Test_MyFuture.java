package ru.sbt.les12_MultiThreading.HomeWork;

import org.junit.Assert;
import org.junit.Test;

public class Test_MyFuture {
    @Test
    public void Test_1(){
        MyFuture future = new MyFuture();
        future.Set(42);
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
                future.Set(42);
            }
        }).start();
        Assert.assertEquals(42, future.get());
    }
}
