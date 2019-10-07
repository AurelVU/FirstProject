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
        boolean flagmin = true;
        boolean flagmax = true;
        boolean flagequil = true;
        for (T item : array)
        {
            if (minValue != null)
                for (Map.Entry<String, Object> entry : minValue.entrySet())
                {
                    Class workingclass = item.getClass();
                    while (!workingclass.getName().equals("java.lang.Object")) {
                        try {
                            Field field = workingclass.getDeclaredField(entry.getKey());
                            field.setAccessible(true);
                            flagmin = flagmin & ((double)field.get(item) > (double)entry.getValue());
                            break;
                        } catch (NoSuchFieldException | IllegalAccessException e) { e.printStackTrace(); }
                        workingclass = workingclass.getSuperclass();
                    }
                }

            if (maxValue != null)
                for (Map.Entry<String, Object> entry : maxValue.entrySet())
                {
                    Class workingclass = item.getClass();
                    while (!workingclass.getName().equals("java.lang.Object")) {
                        try {
                            Field field = workingclass.getField(entry.getKey());
                            field.setAccessible(true);
                            flagmax = flagmax & ((double)field.get(item) < (double)entry.getValue());
                            break;
                        } catch (NoSuchFieldException | IllegalAccessException e) { e.printStackTrace(); }
                        workingclass.getSuperclass();
                    }
                }

            if (equilValue != null)
                for (Map.Entry<String, Object> entry : equilValue.entrySet())
                {
                    Class workingclass = item.getClass();
                    while (!workingclass.getName().equals("java.lang.Object")) {
                        try {
                            Field field = workingclass.getDeclaredField(entry.getKey());
                            field.setAccessible(true);
                            flagequil = flagequil & (field.get(item).equals(entry.getValue()));
                            break;
                        } catch (NoSuchFieldException | IllegalAccessException e) { e.printStackTrace(); }
                        workingclass = workingclass.getSuperclass();
                    }
                }

            if (flagequil && flagmax && flagmin)
                result.add(item);
        }
        return result;
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
