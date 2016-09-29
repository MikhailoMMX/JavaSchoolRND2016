package ru.microbyte.java;

import java.util.Random;

public class RandomNumber {

    final int value = new Random().nextInt();

    public int getValue() {
        return value;
    }
}
