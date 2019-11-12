package com.company.domain;

import com.company.domain.annotations.SQLinformationClass;
import com.company.domain.annotations.SQLinformationVariable;

import java.util.Date;

@SQLinformationClass(name = "employment")
public class Employment extends Entity //Трудоустройство
{
    public Employment(JobApplication jobApplication, JobOffer jobOffer, User user, Employer employer,
                      String employeeReview, String companyReview, Date startDate, Date finishDate) {
        this.jobApplication = jobApplication;
        this.jobOffer = jobOffer;
        this.user = user;
        this.employer = employer;
        this.employeeReview = employeeReview;
        this.companyReview = companyReview;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Employment() {
    }

    public JobApplication getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
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
    private JobApplication jobApplication;
    @SQLinformationVariable(name = "job_offer_id", SQLtype = "INT")
    private JobOffer jobOffer;
    @SQLinformationVariable(name = "user_id", SQLtype = "INT")
    private User user;
    @SQLinformationVariable(name = "employer_id", SQLtype = "INT")
    private Employer employer;
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
        return "id: " + id.toString() + ", логин работника: " + user.getLogin() + ", логин работодателя " + employer.getLogin() +
                ", id предложения" + jobApplication.getId().toString() + ", id заявки " + jobOffer.getId() +
                ", отзыв о сотруднике: " + employeeReview + ", отзыв о работодателе: " + companyReview + ", дата начала работы" +
                startDate.toString() + ", дата окончания работы " + finishDate.toString();
    }
}
