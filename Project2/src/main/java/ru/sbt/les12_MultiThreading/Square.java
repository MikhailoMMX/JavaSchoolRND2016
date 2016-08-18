package ru.sbt.les12_MultiThreading;

public class Square implements Runnable{
    private Object sizeLock = new Object();
    private Object locationLock = new Object();

    private int width;  //
    private int height; //<= \
    private int x;      //    независимые величины
    private int y;      //<= /

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void changeLocation(int x, int y){
        System.out.println("Started");
        synchronized (locationLock){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.x = x;
            this.y = y;
        }
        System.out.println("Changed");
    }

    public void changeSize(int w, int h){
        synchronized (sizeLock){
            this.width = w;
            this.height = h;
        }
    }

    @Override
    public void run() {
        changeLocation(2,3);
    }
}
