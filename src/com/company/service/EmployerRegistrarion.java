package com.company.service;

import com.company.domain.Employer;
import com.company.persistence.DAO;
import com.company.persistence.SimpleWorker;

public class EmployerRegistrarion {
    private DAO<Employer> employerDAO = new SimpleWorker<Employer>();
    void registrarionEmployer(String login, String password)
    {
        Employer employer = new Employer();
    }
}
