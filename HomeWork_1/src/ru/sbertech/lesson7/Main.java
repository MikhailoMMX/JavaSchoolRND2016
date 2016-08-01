package ru.sbertech.lesson7;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) {
        //Test1();
        //Test2();
        //Test3();
        Test4();
    }

    private static void Test4() {
        try {
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:/J:/lecture7/person.jar")}, null);
            Class<?> personClass = urlClassLoader.loadClass("ru.sbt.bvv.lecture7.CalculatorImpl");
            Calculator calculator = new CalculatorImpl();
            calculator.call();

            calculator = (Calculator) personClass.newInstance();
            calculator.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void Test3() {
        try {
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:/J:/lecture7/person.jar")}, null);

            for (int i = 0; i<1_000_000; ++i)       // можно и так
                new URLClassLoader(new URL[]{new URL("file:/J:/lecture7/person.jar")}, null).loadClass("ru.sbt.bvv.lecture7.Person");

            //смотреть jdk_home\jvisualvm.exe

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * Initiating - класслоадер, который НАЧАЛ загрузку
    * Defining - класслоадер, который реально загрузил класс
    *
    * уникальность класса определяется парой - полное имя класса и класслоадером
    * Один класс нельзя привести к другому, если они загружены разными класслоадерами, даже если это один класс
    *
    * ClassNotFoundException - при загрузке вручную класс не найден
    * NoClassDefFoundError - при компиляции класс был, а в рантайме не найден
    * NoSuchMethodError - нет метода, (загружена другая версия)
    * IllegalAccessError - нет доступа, (загружена другая версия и у метода изменился модификатор доступа)
    * */
    private static void Test2(){
        try {
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:/J:/lecture7/person.jar")}, null);
            Class<?> personClass = urlClassLoader.loadClass("ru.sbt.bvv.lecture7.Person");
            System.out.println(personClass.toString());

            Object obj = personClass.newInstance();
            Method m = personClass.getDeclaredMethod("info");
            m.invoke(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void Test1() {
        System.out.println("1");
        Person person = null;
        try {
            person = (Person) Main.class.getClassLoader().loadClass("ru.sbertech.lesson7.Person").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        //new Person();
        System.out.println("2");
        person.setName("MMX");
        System.out.println(person.getName());
    }
}
