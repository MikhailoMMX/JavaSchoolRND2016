package ru.sbertech.lesson6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Student on 28.07.2016.
 */
public class ReflectionTest2 {
    static Map<String, Integer> map = new HashMap<>();   //про это выведется
    public static void main(String[] args) {
        C<Integer> c = new C<>();   //а про это - нет
        try {
            System.out.println(c.getClass().getField("t").getGenericType().getTypeName());
            System.out.println("====");
            Arrays.asList(map.getClass().getTypeParameters()).forEach(System.out::println);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

class C<T>{
    public T t;
}
