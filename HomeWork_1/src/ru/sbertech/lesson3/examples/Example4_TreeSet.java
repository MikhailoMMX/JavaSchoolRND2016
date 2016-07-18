package ru.sbertech.lesson3.examples;

import ru.sbertech.lesson3.Person;
import ru.sbertech.lesson3.PersonFirstLoad;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Created by Student on 18.07.2016.
 */
public class Example4_TreeSet {
    public static void main(String [] args){
        Collection<Person> persons = new TreeSet<>();
        PersonFirstLoad.init(persons);
        System.out.println("Size = " + persons.size());
        PersonFirstLoad.print(persons);

        //System.out.println("--");
    }
}
