package ru.microbyte.decorator.printer;

public class BracketDecorator extends AbstractDecorator{

    public BracketDecorator(PrinterInterface printer) {
        super(printer);
    }

    @Override
    public void print(String text) {
        System.out.print("[");
        printer.print(text);
        System.out.print("]");
    }
}
