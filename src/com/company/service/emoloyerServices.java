package com.company.service;

import com.company.domain.Employer;
import com.company.persistence.DAO;
import com.company.persistence.SimpleWorker;

import java.util.HashMap;
import java.util.List;

public class emoloyerServices {
    protected DAO<Employer> employerDAO = new SimpleWorker<>();

    void registration(String login, String password)
    {
        Employer employer = new Employer(login, password);
        HashMap<String, Object> equilMap = new HashMap<>();
        equilMap.put("login", login);
        List<Employer> employers = employerDAO.readByParams(null, null, equilMap);
        if (employers.size() == 0)
        {
            employerDAO.create(employer);
        }
    }

    List<Employer> getAll()
    {
        return employerDAO.readAll();
    }

    void delete(Long id)
    {
        employerDAO.delete(id);
    }

    Employer getById(Long id)
    {
        return employerDAO.readById(id);
    }

    void change (Long id, String login, String password, int rating, String aboutCompany, String lineActivity)
    {
        Employer employer = new Employer(login, password, rating, aboutCompany, lineActivity);
        employer.setId(id);
        try {
            employerDAO.update(employer);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
