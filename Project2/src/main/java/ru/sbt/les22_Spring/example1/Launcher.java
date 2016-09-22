package ru.sbt.les22_Spring.example1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Launcher {
    static IPrinter printer;
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.sbt.les22_Spring.example1");
        printer = context.getBean(IPrinter.class);
    }
}
