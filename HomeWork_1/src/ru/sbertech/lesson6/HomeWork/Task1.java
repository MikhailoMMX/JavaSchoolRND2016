package ru.sbertech.lesson6.HomeWork;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Task1 {
    private static List<Method> FindGetters(Class cl){
        ArrayList<Method> result = new ArrayList<>();

        //множество имен всех полей
        HashSet<String> fields = new HashSet<>();
        for (Field field : cl.getDeclaredFields())
            fields.add(field.getName().toLowerCase());

        //перебираем методы, ищем те, что начинаются с get, если есть одноименное поле - считаем, что это getter
        for (Method method : cl.getDeclaredMethods())
            if (method.getName().startsWith("get")) {
                String f =  method.getName().substring(3).toLowerCase();
                if (fields.contains(f))
                    result.add(method);
            }

        //и проходим по суперклассам
        if (cl.getSuperclass() != null)
            result.addAll(FindGetters(cl.getSuperclass()));

        return result;
    }
    public static void main(String[] args) {
        Class C = SomeClassB.class;
        List<Method> getters = FindGetters(C);

        System.out.println("Getters for class " + C.getName() + " and all its superclasses:");
        getters.forEach(System.out::println);
    }
}
