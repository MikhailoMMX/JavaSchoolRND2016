package ru.sbt.les21_JDBC.HomeWork;

class Tuple<T extends Comparable> {
    private final T first;
    private final T second;

    public Tuple(T first, T second) {
        if (first.compareTo(second) <= 0) {
            this.first = first;
            this.second = second;
        }
        else{
            this.first = second;
            this.second = first;
        }
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Tuple<?> tuple = (Tuple<?>) o;

        if (!first.equals(tuple.first))
            return false;
        return second.equals(tuple.second);
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }
}
