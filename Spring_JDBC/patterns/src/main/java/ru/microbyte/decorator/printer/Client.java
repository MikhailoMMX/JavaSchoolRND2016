package ru.microbyte.decorator.printer;

public class Client {
    public static void main(String[] args) {
        PrinterInterface printer = new QuotesDecorator(new BracketDecorator(new Printer()));
        printer.print("Hello");

        System.out.println("");

        printer = new BracketDecorator(new QuotesDecorator(new Printer()));
        printer.print("Hello");

    }
}
