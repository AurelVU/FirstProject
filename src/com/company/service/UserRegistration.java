package com.company.service;

import com.company.domain.User;
import com.company.persistence.DAO;
import com.company.persistence.SimpleWorker;

public class UserRegistration {
    private DAO<User> userDAO = new SimpleWorker<User>();
    void Registration(String login, String password)
    {
        User user = new User(login, password, 0, "", "");
        userDAO.create(user);
    }
}
