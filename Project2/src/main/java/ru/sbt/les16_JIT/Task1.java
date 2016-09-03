package ru.sbt.les16_JIT;

import java.util.HashMap;
import java.util.Map;

public class Task1 {
    public static void main(String[] args) {
        int SIZE = 100000;
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < SIZE; i++) {
            map.put(i, inl(i));
        }
    }
    private static String inl(int i){
        return "Value" + i;
    }
}
