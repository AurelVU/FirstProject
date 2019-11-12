package com.company.persistence;

import com.company.domain.Entity;
import com.company.domain.annotations.SQLinformationClass;
import com.company.domain.annotations.SQLinformationVariable;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlDAO<T extends Entity> implements DAO<T> {
    private static final String url = "jdbc:mysql://localhost:3306/labor_exchange?useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "MyNewPass";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public MySqlDAO (Class clazz)
    {
        createTable(clazz);
    }

    private void createTable(Class clazz)
    {
        try {
            con = DriverManager.getConnection(url, user, password);

            Statement statement = con.createStatement();
            String zap = "CREATE TABLE " + ((SQLinformationClass)clazz.getAnnotation(SQLinformationClass.class)).name() + " (";

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

    }

    @Override
    public List<T> readAll() {
        return null;
    }

    @Override
    public List<T> readByParams(HashMap<String, Object> minValue, HashMap<String, Object> maxValue, Map<String, Object> equilValue) {
        return null;
    }

    @Override
    public T readById(Long id) {
        return null;
    }

    @Override
    public void update(T object) throws NoSuchFieldException {

    }

    @Override
    public void create(T object) {

    }
}
