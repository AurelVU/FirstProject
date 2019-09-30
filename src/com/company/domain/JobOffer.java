package com.company.domain;

import java.math.BigDecimal;
import java.util.Date;

public class JobOffer extends AbstractDomain //Предложение работы
{
    private Long id;
    private Employer employer;
    private Date desiredStartTime;
    private Date desiredFinishTime;
    private BigDecimal desiredWage;
    private Date placementDate;
    private String requirements;

    public JobOffer(Employer employer, Date desiredStartTime, Date desiredFinishTime, BigDecimal desiredWage, Date placementDate, String requirements, String other) {
        this.employer = employer;
        this.desiredStartTime = desiredStartTime;
        this.desiredFinishTime = desiredFinishTime;
        this.desiredWage = desiredWage;
        this.placementDate = placementDate;
        this.requirements = requirements;
        this.other = other;
    }

    public JobOffer() {
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Date getDesiredStartTime() {
        return desiredStartTime;
    }

    public void setDesiredStartTime(Date desiredStartTime) {
        this.desiredStartTime = desiredStartTime;
    }

    public Date getDesiredFinishTime() {
        return desiredFinishTime;
    }

    public void setDesiredFinishTime(Date desiredFinishTime) {
        this.desiredFinishTime = desiredFinishTime;
    }

    public BigDecimal getDesiredWage() {
        return desiredWage;
    }

    public void setDesiredWage(BigDecimal desiredWage) {
        this.desiredWage = desiredWage;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    private String other;
}
