package com.company.domain;

import java.util.Date;

public class Employment extends Entity //Трудоустройство
{
    public Employment(JobApplication jobApplication, JobOffer jobOffer, User user, Employer employer, String employeeReview, String companyReview, Date startDate, Date finishDate) {
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

    private Long id;
    private JobApplication jobApplication;
    private JobOffer jobOffer;
    private User user;
    private Employer employer;
    private String employeeReview;
    private String companyReview;
    private Date startDate;
    private Date finishDate;
}
