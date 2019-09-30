package com.company.domain;

public class Employer extends AbstractDomain //Работодатель
{
    private int rating;
    private String aboutCompany;
    private String lineActivity;

    public Employer() {
    }

    public Employer(int rating, String aboutCompany, String lineActivity) {
        this.rating = rating;
        this.aboutCompany = aboutCompany;
        this.lineActivity = lineActivity;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAboutCompany() {
        return aboutCompany;
    }

    public void setAboutCompany(String aboutCompany) {
        this.aboutCompany = aboutCompany;
    }

    public String getLineActivity() {
        return lineActivity;
    }

    public void setLineActivity(String lineActivity) {
        this.lineActivity = lineActivity;
    }
}
