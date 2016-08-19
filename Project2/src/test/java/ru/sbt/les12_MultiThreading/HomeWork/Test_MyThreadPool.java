package ru.sbt.les12_MultiThreading.HomeWork;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Callable;

public class Test_MyThreadPool {
    private MyThreadPool pool;

    @Before
    public void InitPool(){
        pool = new MyThreadPool(4);
    }
    @After
    public void ShutdownPool(){
        pool.setThreadCount(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Test_Illegal_Thread_Count(){
        pool.setThreadCount(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Test_Illegal_Argument_In_Submit(){
        pool.submit(null);
    }

    @Test
    public void Test_Large_Amount_Of_Work(){
        Random random = new Random();
        int SIZE = 1000000;
        int sum = 0;
        List<MyFuture> futures = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j=0; j<10; ++j){
                int next = random.nextInt(10);
                sum += next;
                list.add(next);
            }
            futures.add(pool.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Integer sum = 0;
                    for (int i = 0; i < list.size(); i++) {
                        sum += list.get(i);
                    }
                    return sum;
                }
                List<Integer> list = null;
                public Callable initList(List<Integer> l){
                    list = l;
                    return this;
                }
            }.initList(list)));
        }
        for (int i = 0; i < SIZE; i++) {
            sum -= (Integer)futures.get(i).get();
        }
        Assert.assertEquals(0, sum);
    }

    @Test
    public  void Test_Set_Thread_Count_1(){
        long start = System.currentTimeMillis();
        int TIME = 200;
        int TASKS = 4;
        pool.setThreadCount(1);
        List<MyFuture> f = new ArrayList<>();
        for (int i = 0; i < TASKS; i++) {
            f.add(pool.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(TIME);
                    return null;
                }
            }));
        }
        for (MyFuture fi : f)
            fi.get();
        long end = System.currentTimeMillis();
        Assert.assertTrue((end-start) >= TIME*TASKS*0.9);
    }

    @Test
    public  void Test_Set_Thread_Count_2(){
        long start = System.currentTimeMillis();
        int TIME = 200;
        int TASKS = 10;
        pool.setThreadCount(TASKS+1);
        List<MyFuture> f = new ArrayList<>();
        for (int i = 0; i < TASKS; i++) {
            f.add(pool.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(TIME);
                    return null;
                }
            }));
        }
        for (MyFuture fi : f)
            fi.get();
        long end = System.currentTimeMillis();
        Assert.assertTrue((end-start) <= TIME*1.5);
    }

    @Test
    public void Test_Set_Thread_Count_3(){
        int TIME = 5;
        int TASKS = 100;
        int THREADS = 1;
        Set<Long> set = new HashSet<>();
        pool.setThreadCount(THREADS);
        List<MyFuture> f = new ArrayList<>();
        for (int i = 0; i < TASKS; i++) {
            f.add(pool.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(TIME);
                    return Thread.currentThread().getId();
                }
            }));
        }
        for (MyFuture fi : f)
            set.add((long)fi.get());
        Assert.assertEquals(THREADS, set.size());

        TASKS = 1000;
        THREADS = 10;
        set = new HashSet<>();
        pool.setThreadCount(THREADS);
        f = new ArrayList<>();
        for (int i = 0; i < TASKS; i++) {
            f.add(pool.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(TIME);
                    return Thread.currentThread().getId();
                }
            }));
        }
        for (MyFuture fi : f)
            set.add((long)fi.get());
        Assert.assertEquals(THREADS, set.size());
    }
}
