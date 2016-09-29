package ru.microbyte.xml;

public class PrintingLog {

    public PrintingLog() {
    }

    void before() {
        System.out.println("berfore printing");
    }

    void after() {
        System.out.println("after printing");
    }
}
