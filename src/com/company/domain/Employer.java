package com.company.domain;

import com.company.domain.annotations.SQLinformationClass;
import com.company.domain.annotations.SQLinformationVariable;

@SQLinformationClass(name = "employer")
public class Employer extends AbstractUser //Работодатель
{
    @SQLinformationVariable(name = "rating", SQLtype = "INT")
    private int rating;
    @SQLinformationVariable(name = "about_company", SQLtype = "VARCHAR(100)")
    private String aboutCompany;
    @SQLinformationVariable(name = "line_activity", SQLtype = "VARCHAR(100)")
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
