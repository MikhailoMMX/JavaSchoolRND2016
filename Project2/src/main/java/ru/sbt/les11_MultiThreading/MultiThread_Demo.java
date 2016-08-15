package ru.sbt.les11_MultiThreading;

import java.util.concurrent.TimeUnit;

public class MultiThread_Demo {
    public static void main(String[] args) throws InterruptedException {
//        SomeTask st = new SomeTask();
//        st.run();

//        for (int i = 0; i<2; ++i) {
//            new Thread(new SomeTask()).start();
//        }
//        for (int i = 0; i<4; ++i) {
//            System.out.println("End of Main(" + i+")");
//            TimeUnit.MILLISECONDS.sleep(500);
//            //TimeUnit.MICROSECONDS.sleep(500);
//            //TimeUnit.SECONDS.sleep(5);
//            ///etc.
//            //Thread.sleep(500);
//        }

//        SomeThread st = new SomeThread();
//        st.start();

//        new Thread( new Runnable() {
//            public void run() {
//                System.out.println("I'm here");
//            }
//        }).start();
        //разные спомобы создания потоков и запуска их

//        SomeTask st2 = new SomeTask();
//
//        Thread t1 = new Thread(st2);
//        t1.setPriority(Thread.MIN_PRIORITY);
//
//        Thread t2 = new Thread(st2);
//        t2.setPriority(Thread.NORM_PRIORITY);
//
//        Thread t3 = new Thread(st2);
//        t3.setPriority(Thread.MAX_PRIORITY);
//
//        t1.start();
//        t3.start();
//        t2.start();
        //приоритеты выставлять можно, но используется редко

//        Thread t1 = new Thread(new SomeTask());
//        t1.setDaemon(true);     //<- фоновый поток
//        t1.start();             //не факт, что успеет отработать, т.к. процесс фоновый а не основной

        //Thread t = new Thread(new SomeTask());
        //t.start();
        //t.join();
        //исключение во втором потоке не помешает первому завершиться
        //если запускать через run - помешает

        Thread t = new Thread(new SomeTask());
        t.setUncaughtExceptionHandler((tr, e)-> System.out.println("From " + tr.getName() + " caught " + e));
        //Тоже, но для всех потоков - обработчик по-умолчанию
        //Thread.setDefaultUncaughtExceptionHandler((tr, e)-> System.out.println("From " + tr.getName() + " caught " + e));
        t.start();
        t.join();
        //обработка исключений

        System.out.println("End of main");
    }
}
