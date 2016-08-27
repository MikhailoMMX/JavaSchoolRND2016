package ru.sbt.les14_JavaUtilConcurrent.HomeWork;

public class MyTuple<T extends Comparable> {
    public T First;
    public T Second;

    public MyTuple(T first, T second) {
        if (first.compareTo(second) <= 0) {
            First = first;
            Second = second;
        }
        else{
            First = second;
            Second = first;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyTuple<?> myTuple = (MyTuple<?>) o;

        if (!First.equals(myTuple.First)) return false;
        return Second.equals(myTuple.Second);
    }

    @Override
    public int hashCode() {
        int result = First.hashCode();
        result = 31 * result + Second.hashCode();
        return result;
    }
}
