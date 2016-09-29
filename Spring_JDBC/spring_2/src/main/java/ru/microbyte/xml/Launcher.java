package ru.microbyte.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

//        PrinterManager worker = context.getBean("worker", PrinterManager.class);
//        worker.doWork("Hello world");

        Printer printer = context.getBean(Printer.class);
        printer.out(new PrintData("test"));
    }
}
