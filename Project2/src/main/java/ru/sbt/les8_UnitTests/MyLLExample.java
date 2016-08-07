package ru.sbt.les8_UnitTests;

import java.util.*;

public class MyLLExample {
    private static <T> void printList(MyLinkedList<T> list){
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next().toString() + " ");
        System.out.println();
    }
    private static void printList(Collection<?> col){
        Iterator<?> iterator = col.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next().toString() + " ");
        System.out.println();
    }

    public static void main(String[] args){
        MyLinkedList<Integer> myList = new MyLinkedList<>();

        int i;
        for (i = 0; i< 10; ++i)
            myList.Add(i);

        myList.Add(0, 100);
        myList.Add(11, 100);
        System.out.println("Added 12 numbers:");
        printList(myList);

        System.out.println("3rd element: " + myList.get(3));
        System.out.println("6th element: " + myList.get(6));

        myList.remove(0);   //удалили первый элемент
        myList.remove(5);   //удалили элемент из середины
        myList.remove(9);   //удалили последний элемент

        System.out.println("Removed 3 items:");
        printList(myList);

        System.out.println("3rd element: " + myList.get(3));
        System.out.println("6th element: " + myList.get(6));

        List<Integer> list2 = new ArrayList<>();
        list2.add(-5);
        list2.add(-6);
        list2.add(-7);
        myList.AddAll(list2);

        System.out.println("Added all from a collection:");
        printList(myList);

        list2.clear();
        myList.copy(list2);
        System.out.println("Copied from MyLinkedList:");
        printList(list2);

        try {
            myList.Add(15, 15);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Backward iterator: ");
        Iterator<Integer> iterator = myList.backwardIterator();
        while (iterator.hasNext())
            System.out.print(iterator.next().toString() + " ");
        System.out.println();

        for (i = 0; i<12; ++i)
            myList.remove(0);
        System.out.println("removed everything:");
        printList(myList);
    }
}
