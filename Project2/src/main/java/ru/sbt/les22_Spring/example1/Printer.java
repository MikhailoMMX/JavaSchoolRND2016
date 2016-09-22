package ru.sbt.les22_Spring.example1;

public class Printer implements IPrinter{
    String model;
    @Override
    public void out(String data) {
        System.out.println(model + data);
    }
}
