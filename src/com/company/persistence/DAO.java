package com.company.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DAO<T> {
    void delete(Long id);
    List<T> readAll();
    List<T> readByParams(HashMap<String, Object> minValue, HashMap<String, Object> maxValue,
                         Map<String, Object> equilValue);
    T readById(Long id);
    void update(T object) throws NoSuchFieldException;
    void create(T object);
}
