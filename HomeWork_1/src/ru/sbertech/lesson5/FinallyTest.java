package ru.sbertech.lesson5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Student on 25.07.2016.
 */
public class FinallyTest {
    public static void main(String[] args) {
        //System.out.println(f());
        //G();

        //TryWithResourceOld();

        //TryWithResourceNew();
    }

    private static void TryWithResourceNew() {
        try(FileInputStream in = new FileInputStream("C:\1.txt");
            FileOutputStream out = new FileOutputStream("C:\2.txt");
        ) {
            out.write(in.read());
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
        //закрываемые ресурсы должны реализовывать Autocloseable
    }

    private static void TryWithResourceOld() {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("C:\1.txt");
            out = new FileOutputStream("C:\2.txt");
            out.write(in.read());
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e){
            // NOP
        }
        finally {
            try {
                if (in != null)
                    in.close();
            }
            catch (IOException e) {
                // NOP
            }
            try {
                if (out != null)
                    out.close();
            }
            catch (IOException e){
                // NOP
            }
        }
    }

    private static void G() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("I'm working");
                }
            }
        }).start();
        int a = 1/0;
    }

    private static int f() {
        try {
            //return 1;
            throw new Exception();
        }
        catch (Exception e){
            return 2;
        }
        finally {
            return 3;       //вернется именно это, хоть return, хоть throw был в блоке try.
        }
    }
}
