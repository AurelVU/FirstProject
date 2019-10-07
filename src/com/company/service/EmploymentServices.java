package com.company.service;

import com.company.domain.*;
import com.company.persistence.DAO;
import com.company.persistence.SimpleWorker;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EmploymentServices {
    DAO<Employment> employmentDAO = new SimpleWorker<>();
    public void create(JobApplication jobApplication, JobOffer jobOffer, User user, Employer employer, String employeeReview,
                String companyReview, Date startDate, Date finishDate)
    {
        Employment employment = new Employment(jobApplication, jobOffer, user, employer, employeeReview, companyReview,
                startDate, finishDate);
        employmentDAO.create(employment);
    }

    public void delete(Long id)
    {
        employmentDAO.delete(id);
    }

    public void change(Long id, JobApplication jobApplication, JobOffer jobOffer, User user, Employer employer, String employeeReview,
                String companyReview, Date startDate, Date finishDate)
    {
        Employment employment = new Employment(jobApplication, jobOffer, user, employer, employeeReview, companyReview,
                startDate, finishDate);
        employment.setId(id);
        try {
            employmentDAO.update(employment);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public List<Employment> getAll()
    {
        return employmentDAO.readAll();
    }

    public Employment getById(Long id)
    {
        return employmentDAO.readById(id);
    }

    public List<Employment> getByParams(JobApplication jobApplication, JobOffer jobOffer, User user, Employer employer,
                                 Date startDateMin, Date startDateMax, Date finishDateMin, Date finishDateMax)
    {
        HashMap<String, Object> equilMap = new HashMap<>();
        HashMap<String, Object> minMap = new HashMap<>();
        HashMap<String, Object> maxMap = new HashMap<>();
        if (jobApplication != null)
            equilMap.put("jobApplication", jobApplication);

        if (jobOffer != null)
            equilMap.put("jobOffer", jobOffer);

        if (user != null)
            equilMap.put("user", user);

        if (startDateMax != null)
            maxMap.put("startDate", startDateMax);

        if (startDateMin != null)
            minMap.put("startDate", startDateMin);

        if (finishDateMax != null)
            maxMap.put("finishDate", finishDateMax);

        if (finishDateMin != null)
            minMap.put("finishDate", finishDateMin);

        return employmentDAO.readByParams(minMap, maxMap, equilMap);
    }
}
