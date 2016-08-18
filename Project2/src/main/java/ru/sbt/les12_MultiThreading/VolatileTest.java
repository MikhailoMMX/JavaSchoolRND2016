package ru.sbt.les12_MultiThreading;

public class VolatileTest implements Runnable{
    private /*volatile*/ boolean endFlag = false;
    public void end(){
        endFlag = true;
    }

    public synchronized void setEndFlag(boolean endFlag) {
        this.endFlag = endFlag;
    }

    public synchronized boolean isEndFlag() {

        return endFlag;
    }

    @Override
    public void run(){
        while (!endFlag){
            //do something
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //изначально - зависает
    //добавили synchronized Getter & Setter - все нормально. Но очень медленно
    //volatile - не надо кэшировать объект
}
