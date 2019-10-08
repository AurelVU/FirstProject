package com.company.service;

import com.company.domain.Employer;
import com.company.domain.JobOffer;
import com.company.domain.User;
import com.company.persistence.DAO;
import com.company.persistence.SimpleWorker;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JobOfferServices {
    DAO<JobOffer> jobOfferDAO = new SimpleWorker<>();
    public void create(Employer employer, Date desiredStartTime, Date desiredFinishTime, BigDecimal desiredWage, Date placementDate, String requirements, String other)
    {
        JobOffer jobOffer = new JobOffer(employer, desiredStartTime, desiredFinishTime, desiredWage, placementDate,
                requirements, other);
        jobOfferDAO.create(jobOffer);
    }

    public void delete(Long id)
    {
        jobOfferDAO.delete(id);
    }

    public  void change(Long id, Employer employer, Date desiredStartTime, Date desiredFinishTime, BigDecimal desiredWage,
                Date placementDate, String requirements, String other)
    {
        JobOffer jobOffer = new JobOffer(employer, desiredStartTime, desiredFinishTime, desiredWage, placementDate,
                requirements, other);
        jobOffer.setId(id);
        try {
            jobOfferDAO.update(jobOffer);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public List<JobOffer> getAll()
    {
        return jobOfferDAO.readAll();
    }

    public JobOffer getById(Long id)
    {
        return jobOfferDAO.readById(id);
    }

    public List<JobOffer> getByParams(Employer employer, Date desiredStartTimeMax, Date desiredStartTimeMin,
                               Date desiredFinishTimeMax, Date desiredFinishTimeMin, BigDecimal desiredWageMax,
                               BigDecimal desiredWageMin, Date placementDateMax, Date placementDateMin)
    {
        HashMap<String, Object> equilMap = new HashMap<>();
        HashMap<String, Object> minMap = new HashMap<>();
        HashMap<String, Object> maxMap = new HashMap<>();

        if (employer != null)
        {
            equilMap.put("employer", employer);
        }

        if (desiredStartTimeMin != null)
        {
            minMap.put("desiredStartTime", desiredStartTimeMin);
        }

        if (desiredStartTimeMax != null)
        {
            maxMap.put("desiredStartTime", desiredStartTimeMax);
        }

        if (desiredFinishTimeMin != null)
        {
            minMap.put("desiredFinishTime", desiredFinishTimeMin);
        }

        if (desiredFinishTimeMax != null)
        {
            maxMap.put("desiredFinishTime", desiredFinishTimeMax);}

        if (desiredWageMin != null)
        {
            minMap.put("desiredWage", desiredWageMin);
        }

        if (desiredWageMax != null)
        {
            maxMap.put("desiredWage", desiredWageMax);
        }

        if (placementDateMin != null)
        {
            minMap.put("placementDate", placementDateMin);
        }

        if (placementDateMax != null)
        {
            maxMap.put("placementDate", placementDateMax);
        }

        return jobOfferDAO.readByParams(minMap, maxMap, equilMap);
    }
}
