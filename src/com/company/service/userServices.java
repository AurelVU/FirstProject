package com.company.service;


import com.company.domain.User;
import com.company.persistence.DAO;
import com.company.persistence.SimpleWorker;

import java.util.HashMap;
import java.util.List;

public class userServices {
    protected DAO<User> userDAO = new SimpleWorker<>();

    void registration(String login, String password)
    {
        User user = new User(login, password);
        HashMap<String, Object> equilMap = new HashMap<>();
        equilMap.put("login", login);
        List<User> users = userDAO.readByParams(null, null, equilMap);
        if (users.size() == 0)
        {
            userDAO.create(user);
        }
    }

    List<User> getAll()
    {
        return userDAO.readAll();
    }

    void delete(Long id)
    {
        userDAO.delete(id);
    }

    User getById(Long id)
    {
        return userDAO.readById(id);
    }

    void change (Long id, String login, String password, int rating, String aboutCompany, String lineActivity)
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
