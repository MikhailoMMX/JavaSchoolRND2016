package ru.microbyte.decorator.printer;


public abstract class AbstractDecorator implements PrinterInterface{

    PrinterInterface printer;

    public AbstractDecorator(PrinterInterface printer) {
        this.printer = printer;
    }

    public void print(String text) {
        printer.print(text);
    }
}
