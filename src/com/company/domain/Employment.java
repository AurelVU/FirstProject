package com.company.domain;

import com.company.domain.annotations.SQLinformationClass;
import com.company.domain.annotations.SQLinformationVariable;

import java.util.Date;

@SQLinformationClass(name = "employment")
public class Employment extends Entity //Трудоустройство
{
    public Employment(Long jobApplicationId, Long jobOfferId, Long userId, Long employerId,
                      String employeeReview, String companyReview, Date startDate, Date finishDate) {
        this.jobApplicationId = jobApplicationId;
        this.jobOfferId = jobOfferId;
        this.userId = userId;
        this.employerId = employerId;
        this.employeeReview = employeeReview;
        this.companyReview = companyReview;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Employment() {
    }

    public Long getJobApplicationId() {
        return jobApplicationId;
    }

    public void setJobApplication(Long jobApplicationId) {
        this.jobApplicationId = jobApplicationId;
    }

    public Long getJobOfferId() {
        return jobOfferId;
    }

    public void setJobOffer(Long jobOfferId) {
        this.jobOfferId = jobOfferId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }

    public Long getEmployer() {
        return employerId;
    }

    public void setEmployer(Long employerId) {
        this.employerId = employerId;
    }

    public String getEmployeeReview() {
        return employeeReview;
    }

    public void setEmployeeReview(String employeeReview) {
        this.employeeReview = employeeReview;
    }

    public String getCompanyReview() {
        return companyReview;
    }

    public void setCompanyReview(String companyReview) {
        this.companyReview = companyReview;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @SQLinformationVariable(name = "job_application_id", SQLtype = "INT")
    private Long jobApplicationId;
    @SQLinformationVariable(name = "job_offer_id", SQLtype = "INT")
    private Long jobOfferId;
    @SQLinformationVariable(name = "user_id", SQLtype = "INT")
    private Long userId;
    @SQLinformationVariable(name = "employer_id", SQLtype = "INT")
    private Long employerId;
    @SQLinformationVariable(name = "employee_review", SQLtype = "VARCHAR(1000)")
    private String employeeReview;
    @SQLinformationVariable(name = "company_review", SQLtype = "VARCHAR(1000)")
    private String companyReview;
    @SQLinformationVariable(name = "start_date", SQLtype = "DATE")
    private Date startDate;
    @SQLinformationVariable(name = "finish_date", SQLtype = "DATE")
    private Date finishDate;

    @Override
    public String toString() {
        return "id: " + id.toString() + ", логин работника: " + userId.toString() + ", логин работодателя " + employerId.toString() +
                ", id предложения" + jobApplicationId.toString() + ", id заявки " + jobOfferId.toString() +
                ", отзыв о сотруднике: " + employeeReview + ", отзыв о работодателе: " + companyReview + ", дата начала работы" +
                startDate.toString() + ", дата окончания работы " + finishDate.toString();
    }
}
