package ru.microbyte.decorator.printer;

public class QuotesDecorator extends AbstractDecorator{

    public QuotesDecorator(PrinterInterface printer) {
        super(printer);
    }

    @Override
    public void print(String text) {
        System.out.print("\"");
        printer.print(text);
        System.out.print("\"");
    }
}
