package ru.microbyte.xml;


public class PhotoPrintService implements PrintService {

    Printer printer;

    public void print(PrintData printData) {
        System.out.println("Photo printer ");
        printer.out(printData);
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
