package ru.sbertech.lesson6;

import java.lang.reflect.Field;

public class ReflectionTest3 {

    public static void main(String[] args) {
        Person p = new Person("MyName", "12345");

        try {
            Field f = Person.class.getDeclaredField("login");
            if (f.isAnnotationPresent(ValidLength.class)){
                int min = f.getAnnotation(ValidLength.class).Min();
                ValidLength v = f.getAnnotation(ValidLength.class);
                int max = v.Max();
                System.out.println("login: "+((p.login.length()<= max && p.login.length() >= min)? "ok":"not ok"));
            }
            Field f2 = Person.class.getDeclaredField("password");
            if (f.isAnnotationPresent(ValidLength.class)){
                int min = f2.getAnnotation(ValidLength.class).Min();
                int max = f2.getAnnotation(ValidLength.class).Max();
                System.out.println("Password: " + ((p.password.length()<= max && p.password.length() >= min)? "ok":"not ok"));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

class Person{
    @ValidLength()
    public String login;
    @ValidLength(Min = 8, Max = 20)
    public String password;
    public Person(String l, String pw){
        login = l;
        password = pw;
    }
}