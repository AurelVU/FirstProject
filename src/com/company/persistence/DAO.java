package com.company.persistence;

import com.company.domain.Entity;

import java.util.HashMap;
import java.util.List;

public interface DAO<T> {
    void delete(Long id);
    List<T> readAll();
    List<T> readByParams(HashMap<String, Object> minValue, HashMap<String, Object> maxValue,
                         HashMap<String, Object> equilValue);
    T readById(Long id);
    void update(T object) throws NoSuchFieldException;
    void create(T object);
}
