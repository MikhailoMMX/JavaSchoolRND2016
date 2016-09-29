package ru.microbyte.java;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
@ProfileAnnotation
public class Printer implements IPrinter{

    @PropertyAnnotation("property_value")
    String property;

    @Value("${model}")
    String model;

    @Override
    public void out(String data) {
        System.out.println("Printer '" + model + "' print:  Test");
        System.out.println("property value is " + property);
    }
}
