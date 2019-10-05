package com.company.domain;

public class Employer extends AbstractUser //Работодатель
{
    private int rating;
    private String aboutCompany;
    private String lineActivity;


    public Employer(String login, String password) {
        super(login, password);
        this.rating = 0;
        this.aboutCompany = "";
        this.lineActivity = "";
    }

    public Employer(String login, String password, int rating, String aboutCompany, String lineActivity) {
        super(login, password);
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
