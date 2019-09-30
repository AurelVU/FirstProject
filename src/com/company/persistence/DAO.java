package com.company.persistence;

import java.util.List;

public interface DAO<T> {
    void delete(Long id);
    List<T> readAll();
    Object readById(Long id);
    void update(T object);
    void create(T object);
}
