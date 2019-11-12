package com.company.service;


import com.company.domain.User;
import com.company.persistence.DAO;
import com.company.persistence.InMemDAO;

import java.util.HashMap;
import java.util.List;

public class UserServices {
    protected DAO<User> userDAO = new InMemDAO<>();

    public boolean registration(String login, String password)
    {
        User user = new User(login, password);
        HashMap<String, Object> equilMap = new HashMap<>();
        equilMap.put("login", login);
        List<User> users = userDAO.readByParams(null, null, equilMap);
        if (users.size() == 0)
        {
            userDAO.create(user);
            return true;
        }
        return false;
    }

    public boolean authorization(String login, String password)
    {
        HashMap<String, Object> equil = new HashMap<>();
        equil.put("login", login);
        equil.put("password", password);
        List<User> users = userDAO.readByParams(null, null, equil);
        return users.size() != 0;
    }

    public Long getIdByLogin(String login)
    {
        HashMap<String, Object> equil = new HashMap<>();
        equil.put("login", login);
        List<User> users = userDAO.readByParams(null, null, equil);
        return users.get(0).getId();
    }

    public List<User> getAll()
    {
        return userDAO.readAll();
    }

    public void delete(Long id)
    {
        userDAO.delete(id);
    }

    public User getById(Long id)
    {
        return userDAO.readById(id);
    }

    public void change (Long id, String login, String password, int rating, String aboutCompany, String lineActivity)
    {
        User user = new User(login, password, rating, aboutCompany, lineActivity);
        user.setId(id);
        try {
            userDAO.update(user);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
