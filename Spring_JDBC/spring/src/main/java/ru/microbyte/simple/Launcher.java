package ru.microbyte.simple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(TerminatorQuotes.class).sayQuotes();
        context.getBean("wow", Quotes.class).sayQuotes();
    }
}
