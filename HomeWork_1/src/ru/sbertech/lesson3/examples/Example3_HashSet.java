package ru.sbertech.lesson3.examples;

import ru.sbertech.lesson3.Person;
import ru.sbertech.lesson3.PersonFirstLoad;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Student on 18.07.2016.
 */
public class Example3_HashSet {
    public static void main(String [] args){
        Collection<Person> persons = new HashSet<>();
        PersonFirstLoad.init(persons);
        System.out.println("Size = " + persons.size());
        PersonFirstLoad.print(persons);

        //System.out.println("--");
    }
}
