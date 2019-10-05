package com.company.domain;

import java.util.Date;
import java.math.BigDecimal;

public class JobApplication extends Entity //Заявка на работу
{
    private User user;
    private Date desiredStartTime;

    public JobApplication(User user, Date desiredStartTime, Date desiredFinishTime, BigDecimal desiredWage, Date placementDate, String typeService) {
        this.user = user;
        this.desiredStartTime = desiredStartTime;
        this.desiredFinishTime = desiredFinishTime;
        this.desiredWage = desiredWage;
        this.placementDate = placementDate;
        this.typeService = typeService;
    }

    public JobApplication() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    private Date desiredFinishTime;
    private BigDecimal desiredWage;
    private Date placementDate;
    private String typeService;
}
