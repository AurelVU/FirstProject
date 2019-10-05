package com.company.persistence;

import com.company.domain.Entity;

import java.util.List;

public interface DAO<T> {
    void delete(Long id);
    List<T> readAll();
    T readById(Long id);
    void update(T object) throws NoSuchFieldException;
    void create(T object);
}
