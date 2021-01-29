package models;

public interface DataStore<T> extends Iterable<T> {
    void add(T t);

    int size();
}