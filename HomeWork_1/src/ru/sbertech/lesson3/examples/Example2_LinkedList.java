package ru.sbertech.lesson3.examples;

import ru.sbertech.lesson3.Person;
import ru.sbertech.lesson3.PersonFirstLoad;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Student on 18.07.2016.
 */
public class Example2_LinkedList {
    /*
    По производительности LinkedList во многих случаях оказывается не быстрее ArrayList'а, даже если идет вставка/удаление
    из середины списка. За счет оптимизаций, кэша, отсутствия многократного доступа к элементам по ссылкам
    * */
    public static void main(String [] args){
        LinkedList<Person> persons = new LinkedList<>();
        PersonFirstLoad.init(persons);
        PersonFirstLoad.print(persons);

        System.out.println("--");
        System.out.println("first: " + persons.peek().toString());
        LinkedList<Person> tmp = new LinkedList<>();
        for (int i =0; i<10; ++i)
            tmp.add(persons.poll());
        for (Person p : tmp)
            persons.add(p);
        persons.addFirst(new Person(27L, "Бирюков Виктор Валерьевич", "79185551234"));

        System.out.println("--");

        PersonFirstLoad.print(persons);
    }
}
