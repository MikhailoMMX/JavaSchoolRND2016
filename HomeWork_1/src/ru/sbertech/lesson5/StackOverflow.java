package ru.sbertech.lesson5;

/**
 * Created by Student on 25.07.2016.
 */
public class StackOverflow {
    static int count = 0;
    public static void main(String[] args) {
        System.out.println(count++);
        main(args);
    }
}
// Опция-Xss<Size>
