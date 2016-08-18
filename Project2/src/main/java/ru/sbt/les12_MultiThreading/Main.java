package ru.sbt.les12_MultiThreading;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Test8();
    }

    private static void Test1() {
        Account account = new Account(100);
        int x = 12;
        for (int i = 0; i < x; i++) {
            account.decreaseSaldo(10);
        }
        if (account.getSaldo()<0)
            throw new RuntimeException("Current account overdrafted");
    }

    private static void Test2() {
        Account account = new Account(100);
        int x = 3;
        for (int i = 0; i < x; i++) {
            new Thread(account).start();
        }
    }

    private static void Test3() {
        SelfLockTest slt = new SelfLockTest();
        synchronized (slt) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    slt.doSomething();
                }
            });
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //взаимоблокировка двух потоков
    }
    private static void Test4() {
        Square s = new Square();

        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);

        t1.start();
        t2.start();
    }

    private static void Test5() {
        VolatileTest test = new VolatileTest();
        Thread t1 = new Thread(test);
        t1.start();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.end();
        System.out.println("End of main");
    }

    private static void Test6() {
        Date d1 = new Date();
        System.out.println(d1);
        Man ivan = new Man("Ivan", d1);
        d1.setTime(50);             // пытаемся нарушить условие Immutable
        ivan.getDob().setTime(50);  // пытаемся нарушить условие Immutable

        System.out.println(ivan.getDob());
    }

    private static void Test7() {
        VolatileTest test = new VolatileTest();
        Thread t1 = new Thread(test);
        t1.start();
        t1.interrupt();
        System.out.println("End of main");
        //сработает только при входе или возврате (?) из заблокированного состояния
        //Thread.stop использовать нельзя
        //это все надо учитывать
    }

    private static void Test8() {
        try {
            MonitorExample mi = new MonitorExample();
            Thread t = new Thread(mi::doMessage);
            t.start();
            Thread.sleep(1000);
            mi.sendMessage("A message");
            Thread.sleep(1000);
            t.stop();   //устарело, так как может нарушить согласованность состояния объектов
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
