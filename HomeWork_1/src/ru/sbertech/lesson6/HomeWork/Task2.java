package ru.sbertech.lesson6.HomeWork;

import java.lang.reflect.Proxy;

public class Task2 {
    public static void main(String[] args) {
        IDistance levenshteinDistance = new LevenshteinDistance();
        IDistance loggedLD = (IDistance) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                levenshteinDistance.getClass().getInterfaces(),
                new InvocationHandlerCaching(levenshteinDistance)
                );

        System.out.println("Расстояние Левенштейна для пар строк:");
        System.out.println("(1234567890, ABCDEF) = " + loggedLD.CalculateDistance("1234567890","ABCDEF"));
        System.out.println("(Task1, Task2) = " + loggedLD.CalculateDistance("Task1","Task2"));
        System.out.println("(DoSomething, DoAnything) = " + loggedLD.CalculateDistance("DoSomething","DoAnything"));
        System.out.println("(Prefix_diff, Prefix_DIFF) = " + loggedLD.CalculateDistance("Prefix_diff","Prefix_DIFF"));
        System.out.println("(DoSomething, DoAnything) = " + loggedLD.CalculateDistance("DoSomething","DoAnything"));    // - тут должно сработать кэширование
        System.out.println("(MMX, MMCVXI) = " + loggedLD.CalculateDistance("MMX","MMCVXI"));
        System.out.println("(Task2, Task1) = " + loggedLD.CalculateDistance("Task2","Task1"));                          // - тоже, расстояние симметрично
    }
}
