package ru.sbertech.lesson2;

import java.awt.*;

/**
 * Created by Student on 14.07.2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class SayHello {
    public SayHello(){
        InnerClass ic = new InnerClass();
        System.out.println(ic.is);
    }
    private static String message = ", bro";

    public static String getMessage() {
        return message;
    }
    public static void setMessage(String message) {
        //Затеняющее (shadowing) объявление переменной message
        SayHello.message = message;
    }
    public static void Say() {
        System.out.println("Hello" + message);
    }

    static Point Test = new Point(3,4);
    static void doSomething(){ // метод без модификатора считаетсвя "дружественным"
        // заслоняющее (Obscuring) объявление переменной Test
        System.out.println(Test.x);
        int i = 2;  //i = effective final
        class Local{ private void DoSomething(){int b = i;} };    //локальный класс, не может иметь модификатора, статических объявлений
        //i = 3;  //<= с этой строчкой i - уже не effective final
        Local l = new Local();
        new Thread(new Runnable() {public void run() {return;}}).start(); // тут анонимный класс в параметре конструктора Thread
    }
    private String s = "inner";
    private static class NestedClass{}  // - вложенный класс
    private class InnerClass{           // - вложенный и внутренний класс (т.к. не статический)
        String is = s;
    }

}
class Test{ static int x = 5;}
