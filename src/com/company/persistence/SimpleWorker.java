package com.company.persistence;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<T> readByParams(HashMap<String, Object> minValue, HashMap<String, Object> maxValue,
                                HashMap<String, Object> equilValue) {
        List<T> result = new ArrayList<>();
        Class workclass = result.getClass();
        ParameterizedType type = (ParameterizedType)workclass.getGenericSuperclass();
        Class parameter = (Class)type.getActualTypeArguments()[0];
        boolean flagmin = true;
        boolean flagmax = true;
        boolean flagequil = true;
        for (T item : array)
        {
            if (minValue != null)
                for (Map.Entry<String, Object> entry : minValue.entrySet())
                {
                    try {
                        Field field = item.getClass().getDeclaredField(entry.getKey());
                        field.setAccessible(true);
                        flagmin = flagmin & ((double)field.get(item) > (double)entry.getValue());
                    } catch (NoSuchFieldException | IllegalAccessException e) {

                    }

                }

            if (maxValue != null)
                for (Map.Entry<String, Object> entry : maxValue.entrySet())
                {
                    try {
                        Field field = item.getClass().getDeclaredField(entry.getKey());
                        field.setAccessible(true);
                        flagmax = flagmax & ((double)field.get(item) < (double)entry.getValue());
                    } catch (NoSuchFieldException | IllegalAccessException e) {

                    }
                }

            if (equilValue != null)
                for (Map.Entry<String, Object> entry : equilValue.entrySet())
                {
                    try {
                        Field field = item.getClass().getDeclaredField(entry.getKey());
                        field.setAccessible(true);
                        flagequil = flagequil & (field.get(item).equals(entry.getValue()));
                    } catch (NoSuchFieldException | IllegalAccessException e) {

                    }
                }

            if (flagequil && flagmax && flagmin)
                result.add(item);
        }
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
