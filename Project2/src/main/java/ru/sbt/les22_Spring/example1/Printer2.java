package ru.sbt.les22_Spring.example1;

public class Printer2 implements IPrinter {
    private boolean lowerCase = false;

    public void setLowerCase(boolean lowerCase) {
        this.lowerCase = lowerCase;
    }

    @Override
    public void out(String data) {
        System.out.println(lowerCase ? data.toLowerCase() : data);
    }
}
