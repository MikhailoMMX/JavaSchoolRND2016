package ru.sbertech.lesson7;

public class CalculatorImpl implements Calculator {
    @Override
    public void call() {
        System.out.println("Calc from URLClassloader");
    }
}
