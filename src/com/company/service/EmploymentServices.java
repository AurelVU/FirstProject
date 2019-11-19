package com.company.service;

import com.company.domain.*;
import com.company.persistence.DAO;
import com.company.persistence.InMemDAO;
import com.company.persistence.MySqlDAO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EmploymentServices {
    DAO<Employment> employmentDAO = new MySqlDAO<>(Employment.class);

    public void create(Long jobApplicationId, Long jobOfferId, Long userId, Long employerId, String employeeReview,
                String companyReview, Date startDate, Date finishDate)
    {
        Employment employment = new Employment(jobApplicationId, jobOfferId, userId, employerId, employeeReview, companyReview,
                startDate, finishDate);
        employmentDAO.create(employment);
    }

    public void delete(Long id)
    {
        employmentDAO.delete(id);
    }

    public void change(Long id, Long jobApplicationId, Long jobOfferId, Long userId, Long employerId, String employeeReview,
                String companyReview, Date startDate, Date finishDate)
    {
        Employment employment = new Employment(jobApplicationId, jobOfferId, userId, employerId, employeeReview, companyReview,
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

    public List<Employment> getByParams(Long jobApplicationId, Long jobOfferId, Long userId, Long employerId,
                                 Date startDateMin, Date startDateMax, Date finishDateMin, Date finishDateMax)
    {
        HashMap<String, Object> equilMap = new HashMap<>();
        HashMap<String, Object> minMap = new HashMap<>();
        HashMap<String, Object> maxMap = new HashMap<>();
        if (jobApplicationId != null)
            equilMap.put("jobApplicationId", jobApplicationId);

        if (jobOfferId != null)
            equilMap.put("jobOfferId", jobOfferId);

        if (userId != null)
            equilMap.put("userId", userId);

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
