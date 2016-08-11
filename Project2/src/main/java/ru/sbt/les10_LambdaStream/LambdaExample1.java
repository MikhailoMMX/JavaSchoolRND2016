package ru.sbt.les10_LambdaStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaExample1 {

    @FunctionalInterface
    interface StringPredicate{
        Boolean test(String str);
    }

    public static List<String> filterApples(List<String> inventory, StringPredicate p) {
        List<String> result = new ArrayList<>();
        for(String a : inventory) {
            if(p.test(a)) {
                result.add(a);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> inventory = new ArrayList<>();
        List<String> i2 = filterApples(inventory, (String str) -> "green".equals(str));
        List<Apple> apples = new ArrayList<>();
        //Comparator.comparing(Apple::getWeight); //если завернуть в Sort - сработвает

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        int sum = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(sum);
    }
}
