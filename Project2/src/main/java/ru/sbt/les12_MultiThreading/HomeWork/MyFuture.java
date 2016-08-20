package ru.sbt.les12_MultiThreading.HomeWork;

//Класс, позволяющий дождаться завершения результата обработки задачи потоком
//и получить результат

public class MyFuture <T> {
    private final Object lock = new Object();
    private boolean finished = false;
    private T result;
    private boolean finishedWithException = false;
    private RuntimeException exception;

    /**
     * Метод вызывается из потока после завершения работы и принимает результат в качестве параметра
     * @param t результат выполнения задачи
     */
    protected void set(T t){
        synchronized (lock){
            result = t;
            finished = true;
            lock.notify();
        }
    }

    /**
     * Вызывается из пула потоков после завершения работы и принимает исключение, если оно возникло в ходе работы задачи
     * @param e
     */
    protected void setException(RuntimeException e){
        synchronized (lock) {
            exception = e;
            finishedWithException = true;
            lock.notify();
        }
    }

    /**
     * Метод блокирует выполнение потока до завершения работы задачи и возвращает результат
     * Если в процессе выполнения задачи возникло необработанное исключение - метод генерирует это исключение
     * @return результат выполнения задачи
     */
    public T get(){
        synchronized (lock){
            while (true){
                if (finished)
                    return result;
                else if (finishedWithException)
                    throw exception;
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
