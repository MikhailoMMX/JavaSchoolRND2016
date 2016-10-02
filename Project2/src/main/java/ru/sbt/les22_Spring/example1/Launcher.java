package ru.sbt.les22_Spring.example1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {
    static IPrinter printer;
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        printer = context.getBean(Printer2.class);
        printer.out("Hello, world");
    }
}
