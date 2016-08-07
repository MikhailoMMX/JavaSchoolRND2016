package ru.sbt.les8_UnitTests;

import java.util.Collection;
import java.util.Iterator;

public class MyLinkedList<E> {
    //Элемент двусвязного списка, содержит указатели на предыдущий, следующий элементы списка и сами данные
    private class Item<E>{
        public Item<E> next;
        public Item<E> prev;
        public E data;
        public Item(E data){
            this.data = data;
            this.prev = null;
            this.next = null;
        }
        public Item(E data, Item<E> prev, Item<E> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    //голова, хвост списка
    private Item<E> head;
    private Item<E> tail;

    //количество элементов, используется при оптимизации доступа к элементам
    private int count;
    public MyLinkedList(){
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Находит элемент с указанным индексом
     * @param index
     * @return
     */
    private Item<E> GetItem(int index){
        Item<E> cur;
        int n;
        if (index <= count/2){
            n = 0;
            cur = head;
            while (n<index) {
                n += 1;
                cur = cur.next;
            }
        }
        else{
            n = count-1;
            cur = tail;
            while (n>index)
            {
                n -= 1;
                cur = cur.prev;
            }
        }
        return cur;
    }

    /**
     * Добавляет элемент в конец списка
     * @param e добавляемый элемент
     */
    public void Add(E e) {
        Item<E> item = new Item<E>(e);
        count += 1;
        if (tail == null)
        {
            head = item;
            tail = item;
        }
        else
        {
            tail.next = item;
            item.prev = tail;
            tail = item;
        }
    }

    /**
     * Добавляет элемент в список в указанную позицию
     * @param index позиция, от 0 до количества элементов включительно
     * @param e вставляемый элемент
     */
    public void Add(int index, E e){
        if (index<0 || index>count)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        if (count == 0 || index == count) {     //список пуст или добавляем в конец - вызываем метод добавления в конец
            Add(e);
            return;
        }

        //вставляем перед определенным элементом - item
        count += 1;
        Item<E> item = GetItem(index);
        Item<E> newItem = new Item<E>(e, item.prev, item);
        if (item.prev != null)
            item.prev.next = newItem;
        else
            head = newItem;
        item.prev = newItem;
    }

    /**
     * Возвращает элемент с указанным индексом
     * @param index
     * @return
     */
    public E get(int index){
        if (index<0 || index >= count)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        return GetItem(index).data;
    }

    /**
     * Удаляет элемент с указанным индексом из списка
     * @param index индекс от 0 до количество_элементов - 1
     * @return удаленный элемент
     */
    public E remove(int index){
        if (index<0 || index >= count)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        count -= 1;
        Item<E> item = GetItem(index);
        if (item == head)
        {
            head = head.next;
            if (head != null)
                head.prev = null;
            else
                tail = null;
        }
        else if (item == tail)
        {
            tail = tail.prev;
            if (tail != null)
                tail.next = null;
            else
                head = null;
        }
        else
        {
            item.prev.next = item.next;
            item.next.prev = item.prev;
        }
        return item.data;
    }

    private class MyLLIterator implements Iterator<E>{
        private Item<E> cur;
        public MyLLIterator(MyLinkedList<E> list){
            cur = list.head;
        }
        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public E next() {
            E data = cur.data;
            cur = cur.next;
            return data;
        }
    }
    private class MyLLBackwardIterator implements Iterator<E>{
        private Item<E> cur;
        public MyLLBackwardIterator(MyLinkedList<E> list){
            cur = list.tail;
        }
        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public E next() {
            E data = cur.data;
            cur = cur.prev;
            return data;
        }
    }

    /**
     * Возвращает итератор по списку
     * @return
     */
    public Iterator<E> iterator(){
        return new MyLLIterator(this);
    }
    public Iterator<E> backwardIterator(){
        return new MyLLBackwardIterator(this);
    }

    /**
     * Копирует указанную коллекцию в этот список
     * @param c коллекция
     * @return ???
     */
    public boolean AddAll(Collection<? extends E> c){
        for (E e : c)
            Add(e);
        return true;    //???
    }

    /**
     * Копирует этот список в указанную коллекцию
     * @param c коллееция
     * @return ???
     */
    public boolean copy(Collection<? super E> c) {
        Iterator<E> it = iterator();
        while (it.hasNext())
            c.add(it.next());
        return true;    //???
    }
}
