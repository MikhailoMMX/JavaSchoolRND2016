package ru.sbertech.lesson3.beforeGenerics;

/**
 * Created by Student on 18.07.2016.
 */
public class Apple {
    final Long id = counter++;

    public Long getId() {
        return id;
    }

    static long counter = new Long(0L);
}
