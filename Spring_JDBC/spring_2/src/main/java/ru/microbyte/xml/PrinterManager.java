package ru.microbyte.xml;

import org.springframework.stereotype.Component;

@Component
public class PrinterManager {

    private PrintService printService;

    void doWork(String text) {
        printService.print(new PrintData(text));
    }

    public void setPrintService(PrintService printService) {
        this.printService = printService;
    }
}
