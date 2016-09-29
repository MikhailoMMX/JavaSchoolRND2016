package ru.microbyte.xml;

public class BookPrintService implements PrintService {

    private Printer printer;

    public BookPrintService(Printer printer) {
        this.printer = printer;
    }

    public void print(PrintData printData) {
        printer.out(printData);
    }

}
