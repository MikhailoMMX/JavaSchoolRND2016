package ru.microbyte.java;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Launcher {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.microbyte");
        IPrinter printer = context.getBean(IPrinter.class);
        printer.out("Hello world");

//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//
//        RandomNumberHolder randomNumberHolder = context.getBean(RandomNumberHolder.class);
//        System.out.println(randomNumberHolder.getRandomNumber().getValue());
//        System.out.println(randomNumberHolder.getRandomNumber().getValue());
//        System.out.println(randomNumberHolder.getRandomNumber().getValue());
//        System.out.println(randomNumberHolder.getRandomNumber().getValue());
//        System.out.println(randomNumberHolder.getRandomNumber().getValue());

    }
}
