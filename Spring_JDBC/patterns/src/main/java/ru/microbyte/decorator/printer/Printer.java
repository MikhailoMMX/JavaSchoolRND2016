package ru.microbyte.decorator.printer;

public class Printer implements PrinterInterface {

    public void print(String text) {
        System.out.print(text);
    }
}
