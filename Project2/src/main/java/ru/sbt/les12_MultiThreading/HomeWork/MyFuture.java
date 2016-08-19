package ru.sbt.les12_MultiThreading.HomeWork;

//Класс, позволяющий дождаться завершения результата обработки задачи потоком
//и получить результат

public class MyFuture <T> {
    private final Object lock = new Object();
    private boolean finished = false;
    private T result;

    /**
     * Метод вызывается из потока после завершения работы и принимает результат в качестве параметра
     * @param t результат выполнения задачи
     */
    protected void Set(T t){
        synchronized (lock){
            result = t;
            finished = true;
            lock.notify();
        }
    }

    /**
     * Метод блокирует выполнение потока до завершения работы задачи и возвращает результат
     * @return результат выполнения задачи
     */
    public T get(){
        synchronized (lock){
            while (true){
                if (finished)
                    return result;
                else
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        return null;
                    }
            }
        }
    }
}
