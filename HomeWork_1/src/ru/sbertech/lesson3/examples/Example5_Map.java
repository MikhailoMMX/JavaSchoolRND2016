package ru.sbertech.lesson3.examples;

import ru.sbertech.lesson3.Person;
import ru.sbertech.lesson3.PersonFirstLoad;

import java.util.*;

/**
 * Created by Student on 18.07.2016.
 */
public class Example5_Map {
    public static void main(String [] args){
        System.out.println("LinkedHashMap");
        Map<String, Person> persons = new LinkedHashMap<>();
        PersonFirstLoad.init(persons);
        System.out.println("Size = " + persons.size());
        PersonFirstLoad.print(persons);

        System.out.println("TreeMap");
        Map<String, Person> persons2 = new TreeMap<>();
        PersonFirstLoad.init(persons2);
        System.out.println("Size = " + persons2.size());
        PersonFirstLoad.print(persons2);

        System.out.println("HashMap");
        Map<String, Person> persons3 = new TreeMap<>();
        PersonFirstLoad.init(persons3);
        System.out.println("Size = " + persons3.size());
        PersonFirstLoad.print(persons3);

        System.out.println("--");
        System.out.println(persons.get("12"));
    }
}
