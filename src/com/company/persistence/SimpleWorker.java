package com.company.persistence;

import java.util.ArrayList;
import java.util.List;

import com.company.domain.Entity;

public class SimpleWorker<T extends Entity> implements DAO<T> {
    private List<T> array;

    public SimpleWorker() {
        array = new ArrayList<>();
    }
    @Override
    public void delete(Long id) {
        array.set(id.intValue(), null);
    }

    @Override
    public List<T> readAll() {
        return array;
    }

    @Override
    public T readById(Long id) {
        return array.get(id.intValue());
    }

    @Override
    public void update(T object) {
        array.set(object.getId().intValue(), object);
    }

    @Override
    public void create(T object) {
        object.setId((long) array.size());
        array.add(object.getId().intValue(), object);
    }
}
