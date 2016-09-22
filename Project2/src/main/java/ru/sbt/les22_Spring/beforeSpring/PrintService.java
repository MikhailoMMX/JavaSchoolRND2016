package ru.sbt.les22_Spring.beforeSpring;

public class PrintService {
    private Printer printer;
    void print(String text){
        printer.out(text);
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
