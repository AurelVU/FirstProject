package com.company.domain;

import com.company.domain.annotations.SQLinformationClass;
import com.company.domain.annotations.SQLinformationVariable;

import java.util.Date;
import java.math.BigDecimal;

@SQLinformationClass(name = "job_application")
public class JobApplication extends Entity //Заявка на работу
{
    @SQLinformationVariable(name = "user_id", SQLtype = "INT")
    private Long userId;
    @SQLinformationVariable(name = "desired_start_time", SQLtype = "DATE")
    private Date desiredStartTime;

    public JobApplication(Long userId, Date desiredStartTime, Date desiredFinishTime, BigDecimal desiredWage, Date placementDate, String typeService) {
        this.userId = userId;
        this.desiredStartTime = desiredStartTime;
        this.desiredFinishTime = desiredFinishTime;
        this.desiredWage = desiredWage;
        this.placementDate = placementDate;
        this.typeService = typeService;
    }

    public JobApplication() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
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

    @SQLinformationVariable(name = "desired_finish_time", SQLtype = "DATE")
    private Date desiredFinishTime;
    @SQLinformationVariable(name = "desired_wage", SQLtype = "INT(20)")
    private BigDecimal desiredWage;
    @SQLinformationVariable(name = "placement_date", SQLtype = "DATE")
    private Date placementDate;
    @SQLinformationVariable(name = "type_service", SQLtype = "VARCHAR(100)")
    private String typeService;

    @Override
    public String toString() {
        return "id " + id.toString() + ", Создатель " + userId.toString() + ", начало работы " + desiredStartTime + ", дата завершения работы " +
                desiredFinishTime + ", оплата " + desiredWage.toString() + ", дата размещения " + placementDate.toString()
                + ", тип услуги " + typeService;
    }
}
