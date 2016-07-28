package ru.sbertech.lesson6;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 28.07.2016.
 */
public class ProxyExampleLoggedList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        List<String> loggedList = (List<String>)
                Proxy.newProxyInstance(
                        ClassLoader.getSystemClassLoader(),
                        list.getClass().getInterfaces(),
                        new InvocationHandlerImpl(list)
                );

        loggedList.add("1");
        loggedList.add("2");
        loggedList.add("42");
        loggedList.remove(0);
        System.out.println("== List: ==");
        list.forEach(System.out::println);
    }
}
