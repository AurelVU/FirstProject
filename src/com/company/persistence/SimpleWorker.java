package com.company.persistence;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

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

        for (T item : array)
        {
            boolean flagmin = true;
            boolean flagmax = true;
            boolean flagequil = true;
            if (minValue != null)
                for (Map.Entry<String, Object> entry : minValue.entrySet())
                {
                    Class workingclass = item.getClass();
                    while (!workingclass.getName().equals("java.lang.Object")) {
                        try {
                            Field field = workingclass.getDeclaredField(entry.getKey());
                            field.setAccessible(true);
                            List<Method> methods = Arrays.asList(field.get(item).getClass().getMethods());
                            Method useMethod = null;
                            for(Method method : methods){
                                if (method.getName().equals("compareTo")){
                                    useMethod = method;
                                    useMethod.setAccessible(true);
                                    break;
                                }
                            }
                            if (useMethod != null){
                                flagmin = flagmin & ((Integer)useMethod.invoke(field.get(item), entry.getValue()) > 0);
                                break;
                            }
                        } catch (NoSuchFieldException | IllegalAccessException  | InvocationTargetException e) { /*e.printStackTrace();*/ }
                        workingclass = workingclass.getSuperclass();
                    }
                }

            if (maxValue != null)
                for (Map.Entry<String, Object> entry : maxValue.entrySet())
                {
                    Class workingclass = item.getClass();
                    while (!workingclass.getName().equals("java.lang.Object")) {
                        try {
                            Field field = workingclass.getDeclaredField(entry.getKey());
                            field.setAccessible(true);
                            List<Method> methods = Arrays.asList(field.get(item).getClass().getMethods());
                            Method useMethod = null;
                            for(Method method : methods){
                                if (method.getName().equals("compareTo")){
                                    useMethod = method;
                                    useMethod.setAccessible(true);
                                    break;
                                }
                            }
                            flagmax = flagmax & ((Integer)useMethod.invoke(field.get(item), entry.getValue()) < 0);
                            break;
                        } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException e) { /*e.printStackTrace();*/ }
                        workingclass = workingclass.getSuperclass();
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
                        } catch (NoSuchFieldException | IllegalAccessException e) { /*e.printStackTrace();*/ }
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
        object.setId((long)array.size());
        array.add(object.getId().intValue(), object);
    }
}
