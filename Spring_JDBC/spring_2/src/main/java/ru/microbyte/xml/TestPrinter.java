package ru.microbyte.xml;

public class TestPrinter implements Printer {
    public void out(PrintData printData) {
        System.out.println("Test printer: " + printData.getData());
    }
}
