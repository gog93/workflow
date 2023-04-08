package com.example.workflow.mapper;

public interface Mapper<T, V> {

    V mapToDTO(T t);

    T mapToOBJ(V v);

}
