package ru.abdyabdya.es_cqrs;

public interface ApplierFunction<T> {
    public T apply(T object, Command event);
}
