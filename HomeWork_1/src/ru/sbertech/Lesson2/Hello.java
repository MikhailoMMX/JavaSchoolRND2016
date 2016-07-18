package ru.sbertech.lesson2;
/**
 * Created by Student on 14.07.2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class Hello {
    static SayHello sh;
    public static void main(String[] args){
        sh = new SayHello();
        sh.Say();
    }
}
