package ru.sbt.les12_MultiThreading.HomeWork;

//Поток
//хранит ссылку на основной рабочий объект и передает ему команду для остановки

public class MyThread extends Thread{
    private MyWorker worker;

    public MyThread(MyWorker worker) {
        super(worker);
        this.worker = worker;
    }

    @Override
    public void interrupt() {
        worker.interrupt();
    }

    @Override
    public boolean isInterrupted() {
        return worker.isInterrupting();
    }
}
