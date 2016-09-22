package ru.sbt.les22_Spring.example1;

public class PrinterManager {
    private static PrintService printService;
    private static Printer printer;

    public static void main(String[] args) {
        printer = new Printer();
        printService = new PrintService();
        printService.setPrinter(printer);
        //^ технические действия, неудобно

        doWork("Test");
    }
    private static void doWork(String text){
        printService.print(text);
    }
}
