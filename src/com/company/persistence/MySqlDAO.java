package com.company.persistence;

import com.company.domain.Entity;
import com.company.domain.annotations.SQLinformationClass;
import com.company.domain.annotations.SQLinformationVariable;

import java.lang.reflect.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlDAO<T extends Entity> implements DAO<T> {
    private static final String url = "jdbc:mysql://localhost:3306/labor_exchange?useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "MyNewPass";

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    public MySqlDAO (Class clazz)
    {
        createTable(clazz);
    }
    private Class mainClazz = null;
    private void createTable(Class clazz)
    {
        mainClazz = clazz;
        try {
            con = DriverManager.getConnection(url, user, password);

            Statement statement = con.createStatement();
            String zap = "CREATE TABLE IF NOT EXISTS " + ((SQLinformationClass)clazz.getAnnotation(SQLinformationClass.class)).name() + " (";

            while (!clazz.getName().equals("java.lang.Object")) {

                Field[] field = clazz.getDeclaredFields();
                for (Field f : field) {
                    f.setAccessible(true);
                    zap += ((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name() + " " +
                            ((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).SQLtype() + " " +
                            ((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).SQLparams();
                    if (!(f.equals(field[field.length - 1]) && clazz.getSuperclass().getName().equals("java.lang.Object")))
                        zap += ", ";
                }
                clazz = clazz.getSuperclass();
            }
            zap += ");";

            statement.execute(zap);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String zap = "DELETE FROM " + ((SQLinformationClass)mainClazz.getAnnotation(SQLinformationClass.class)).name() +
                " WHERE id = " + id.toString() + ";";
        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.execute(zap);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> readAll() {
        String zap = "SELECT * FROM " + ((SQLinformationClass)mainClazz.getAnnotation(SQLinformationClass.class)).name() +
                ";";
        List<T> res = new ArrayList<>();
        Statement statement = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(zap);
            while (rs.next())
            {
                try {
                    Object obj = mainClazz.newInstance();
                    Class clazz = mainClazz;
                    while (!clazz.getName().equals("java.lang.Object")) {
                        Field[] field = clazz.getDeclaredFields();
                        for (Field f : field) {
                            f.setAccessible(true);
                            if (f.getType().getName().indexOf("Long") != -1)
                            {
                                f.set(obj, rs.getLong(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("int") != -1)
                            {
                                f.set(obj, rs.getInt(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("double") != -1)
                            {
                                f.set(obj, rs.getDouble(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("float") != -1)
                            {
                                f.set(obj, rs.getFloat(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("Long") != -1)
                            {
                                f.set(obj, rs.getLong(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("String") != -1)
                            {
                                f.set(obj, rs.getString(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("Date") != -1)
                            {
                                f.set(obj, rs.getDate(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("BigDecimal") != -1)
                            {
                                f.set(obj, rs.getBigDecimal(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else
                            {
                            }
                        }
                        clazz = clazz.getSuperclass();
                    }
                    res.add((T) obj);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<T> readByParams(HashMap<String, Object> minValue, HashMap<String, Object> maxValue, Map<String, Object> equilValue) {
        String zap = "SELECT * FROM " + ((SQLinformationClass)mainClazz.getAnnotation(SQLinformationClass.class)).name() +
                " WHERE ";


        String zap_min = "";
        String zap_max = "";
        String zap_equil = "";

        if (minValue != null)
            for (Map.Entry<String, Object> entry : minValue.entrySet()) {
                Class workingclass = mainClazz;
                while (!workingclass.getName().equals("java.lang.Object")) {
                    try {
                        Field field = workingclass.getDeclaredField(entry.getKey());
                        field.setAccessible(true);
                        zap_min += " " + ((SQLinformationVariable)field.getAnnotation(SQLinformationVariable.class)).name() +
                                 " > ";
                        if (entry.getValue().getClass().getName().indexOf("Date") != -1)
                        {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            zap_min += ("'" + dateFormat.format(entry.getValue()) + "'");
                        }
                        else
                            zap_min += entry.getValue().toString();
                        zap_min += " AND ";
                    } catch (NoSuchFieldException e) { /*e.printStackTrace();*/ }
                    workingclass = workingclass.getSuperclass();
                }
            }

        if (maxValue != null)
            for (Map.Entry<String, Object> entry : maxValue.entrySet()) {
                Class workingclass = mainClazz;
                while (!workingclass.getName().equals("java.lang.Object")) {
                    try {
                        Field field = workingclass.getDeclaredField(entry.getKey());
                        field.setAccessible(true);
                        zap_max += " " + ((SQLinformationVariable)field.getAnnotation(SQLinformationVariable.class)).name() +
                                " < ";
                        if (entry.getValue().getClass().getName().indexOf("Date") != -1)
                        {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            zap_max += ("'" + dateFormat.format(entry.getValue()) + "'");
                        }
                        else
                            zap_max += entry.getValue().toString();
                        zap_max += " AND ";
                    } catch (NoSuchFieldException e) { /*e.printStackTrace();*/ }
                    workingclass = workingclass.getSuperclass();
                }
            }

        if (equilValue != null)
            for (Map.Entry<String, Object> entry : equilValue.entrySet()) {
                Class workingclass = mainClazz;
                while (!workingclass.getName().equals("java.lang.Object")) {
                    try {
                        Field field = workingclass.getDeclaredField(entry.getKey());
                        field.setAccessible(true);
                        zap_equil += " " +
                                ((SQLinformationVariable)field.getAnnotation(SQLinformationVariable.class)).name() +
                                " = ";
                        if (entry.getValue().getClass().getName().indexOf("Date") != -1)
                        {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            zap_equil += ("'" + dateFormat.format(entry.getValue()) + "'");
                        }
                        else
                            zap_equil += (entry.getValue().getClass().getName().indexOf("String") != -1? "'" : "") +
                                entry.getValue().toString() +
                                (entry.getValue().getClass().getName().indexOf("String") != -1? "'" : "");
                        zap_equil += " AND ";
                    } catch (NoSuchFieldException e) { /*e.printStackTrace();*/ }
                    workingclass = workingclass.getSuperclass();
                }
            }

        zap = zap + zap_max + zap_min + zap_equil;

        zap = zap.substring(0, zap.lastIndexOf("AND"));
        zap += ";";

        List<T> res = new ArrayList<>();
        Statement statement = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(zap);
            while (rs.next())
            {
                try {
                    Object obj = mainClazz.newInstance();
                    Class clazz = mainClazz;
                    while (!clazz.getName().equals("java.lang.Object")) {
                        Field[] field = clazz.getDeclaredFields();
                        for (Field f : field) {
                            f.setAccessible(true);
                            if (f.getType().getName().indexOf("Long") != -1)
                            {
                                f.set(obj, rs.getLong(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("int") != -1)
                            {
                                f.set(obj, rs.getInt(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("double") != -1)
                            {
                                f.set(obj, rs.getDouble(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("float") != -1)
                            {
                                f.set(obj, rs.getFloat(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("Long") != -1)
                            {
                                f.set(obj, rs.getLong(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("String") != -1)
                            {
                                f.set(obj, rs.getString(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("Date") != -1)
                            {
                                f.set(obj, rs.getDate(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("BigDecimal") != -1)
                            {
                                f.set(obj, rs.getBigDecimal(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else
                            {
                            }
                        }
                        clazz = clazz.getSuperclass();
                    }
                    res.add((T) obj);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public T readById(Long id) {
        String zap = "SELECT * FROM " + ((SQLinformationClass)mainClazz.getAnnotation(SQLinformationClass.class)).name() +
                " WHERE id = " + id.toString() + ";";


        List<T> res = new ArrayList<>();
        Statement statement = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(zap);
            while (rs.next())
            {
                try {
                    Object obj = mainClazz.newInstance();
                    Class clazz = mainClazz;
                    while (!clazz.getName().equals("java.lang.Object")) {
                        Field[] field = clazz.getDeclaredFields();
                        for (Field f : field) {
                            f.setAccessible(true);
                            if (f.getType().getName().indexOf("Long") != -1)
                            {
                                f.set(obj, rs.getLong(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("int") != -1)
                            {
                                f.set(obj, rs.getInt(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("double") != -1)
                            {
                                f.set(obj, rs.getDouble(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("float") != -1)
                            {
                                f.set(obj, rs.getFloat(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("Long") != -1)
                            {
                                f.set(obj, rs.getLong(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("String") != -1)
                            {
                                f.set(obj, rs.getString(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("Date") != -1)
                            {
                                f.set(obj, rs.getDate(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else if (f.getType().getName().indexOf("BigDecimal") != -1)
                            {
                                f.set(obj, rs.getBigDecimal(((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name()));
                            }
                            else
                            {
                            }
                        }
                        clazz = clazz.getSuperclass();
                    }
                    res.add((T) obj);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res.size() == 0)
            return null;
        else
            return res.get(0);
    }

    @Override
    public void update(T object) throws NoSuchFieldException {
        String zap = "UPDATE " + ((SQLinformationClass)mainClazz.getAnnotation(SQLinformationClass.class)).name()
                + " SET ";

        String zap_params = "";

        Class clazz = mainClazz;
        while (!clazz.getName().equals("java.lang.Object")) {
            Field[] field = clazz.getDeclaredFields();
            for (Field f : field) {
                f.setAccessible(true);
                if (((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name().equals("id"))
                {
                    continue;
                } else {
                    zap_params += ((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name() + " = ";
                    try {
                        if (f.get(object).getClass().getName().indexOf("String") != -1)
                            zap_params += "'";
                        if (f.get(object).getClass().getName().indexOf("Date") != -1)
                        {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            zap_params += ("'" + dateFormat.format(f.get(object)) + "'");
                        }
                        else
                            zap_params += f.get(object).toString();
                        if (f.get(object).getClass().getName().indexOf("String") != -1)
                            zap_params += "'";
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                zap_params += ", ";
            }
            clazz = clazz.getSuperclass();
        }
        zap_params = zap_params.substring(0, zap_params.lastIndexOf(","));

        zap += zap_params;
        zap += " WHERE id = " + object.getId().toString() + ";";

        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.execute(zap);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(T object) {
        String zap = "INSERT INTO " + ((SQLinformationClass)mainClazz.getAnnotation(SQLinformationClass.class)).name() + " ";
        String params = "(";
        String values = ") VALUES (";
        Class clazz = mainClazz;
        while (!clazz.getName().equals("java.lang.Object")) {
            Field[] field = clazz.getDeclaredFields();
            for (Field f : field) {
                f.setAccessible(true);
                if (((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name().equals("id"))
                {
                    continue;
                } else {
                    params += ((SQLinformationVariable)f.getAnnotation(SQLinformationVariable.class)).name();
                    try {
                        if (f.get(object).getClass().getName().indexOf("String") != -1)
                            values += "'";
                        if (f.get(object).getClass().getName().indexOf("Date") != -1)
                        {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            values += ("'" + dateFormat.format(f.get(object)) + "'");
                        }
                        else
                            values += f.get(object).toString();
                        if (f.get(object).getClass().getName().indexOf("String") != -1)
                            values += "'";
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
               values += ", ";
               params += ", ";

            }
            clazz = clazz.getSuperclass();
        }

        params = params.substring(0, params.lastIndexOf(","));

        values = values.substring(0, values.lastIndexOf(","));


        zap += params;
        zap += values;
        zap += ");";

        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.execute(zap);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
