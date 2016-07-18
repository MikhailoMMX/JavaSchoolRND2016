package ru.sbertech.lesson3.examples;

import ru.sbertech.lesson3.Person;
import ru.sbertech.lesson3.PersonFirstLoad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 18.07.2016.
 */
public class Example1_ArrayList {

    public static void main(String [] args){
        List<Person> persons = new ArrayList<>();
        PersonFirstLoad.init(persons);

        //PersonFirstLoad.print(persons);

        //System.out.println(persons.get(11).toString());

        //PersonFirstLoad.print(persons.subList(10, 15));

        //List<Person> subPersons = persons.subList(10, 15);
        //subPersons.add(new Person(27L, "Бирюков Виктор Валерьевич", "79185551234"));
        //System.out.println(persons.containsAll(subPersons));
        //true - sublist возвращает ссылку на подмассив в исходном массиве. При добавлении - добавляет в исходный массив

        //System.out.println(persons.contains(new Person(27L, "Бирюков Виктор Валерьевич", "79185551234")));


    }
}
