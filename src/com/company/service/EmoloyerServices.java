package com.company.service;

import com.company.domain.Employer;
import com.company.persistence.DAO;
import com.company.persistence.SimpleWorker;

import java.util.HashMap;
import java.util.List;

public class EmoloyerServices {
    protected DAO<Employer> employerDAO = new SimpleWorker<>();

    public boolean registration(String login, String password)
    {
        Employer employer = new Employer(login, password);
        HashMap<String, Object> equilMap = new HashMap<>();
        equilMap.put("login", login);
        List<Employer> employers = employerDAO.readByParams(null, null, equilMap);
        if (employers.size() == 0)
        {
            employerDAO.create(employer);
            return true;
        }
        return false;
    }

    public Long getIdByLogin(String login)
    {
        HashMap<String, Object> equil = new HashMap<>();
        equil.put("login", login);
        List<Employer> employers = employerDAO.readByParams(null, null, equil);
        return employers.get(0).getId();
    }

    public boolean authorization(String login, String password)
    {
        HashMap<String, Object> equil = new HashMap<>();
        equil.put("login", login);
        equil.put("password", password);
        List<Employer> employers = employerDAO.readByParams(null, null, equil);
        return employers.size() != 0;
    }

    public List<Employer> getAll()
    {
        return employerDAO.readAll();
    }

    public void delete(Long id)
    {
        employerDAO.delete(id);
    }

    public Employer getById(Long id)
    {
        return employerDAO.readById(id);
    }

    public void change (Long id, String login, String password, int rating, String aboutCompany, String lineActivity)
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
