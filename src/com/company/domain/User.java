package com.company.domain;

import com.company.domain.annotations.SQLinformationClass;
import com.company.domain.annotations.SQLinformationVariable;

@SQLinformationClass(name = "user")
public class User extends AbstractUser //Пользователь
{
    @SQLinformationVariable(name = "rating", SQLtype = "INT")
    private int rating;
    @SQLinformationVariable(name = "about_employee", SQLtype = "VARCHAR(100)")
    private String aboutEmployee;
    @SQLinformationVariable(name = "line_activity", SQLtype = "VARCHAR(100)")
    private String lineActivity;

    public User(String login, String password, int rating, String aboutEmployee, String lineActivity) {
        super(login, password);
        this.rating = rating;
        this.aboutEmployee = aboutEmployee;
        this.lineActivity = lineActivity;
    }

    public  User(String login, String password) {
        super(login, password);
        this.rating = 0;
        this.aboutEmployee = "";
        this.lineActivity = "";
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAboutEmployee() {
        return aboutEmployee;
    }

    public void setAboutEmployee(String aboutEmployee) {
        this.aboutEmployee = aboutEmployee;
    }

    public String getLineActivity() {
        return lineActivity;
    }

    public void setLineActivity(String lineActivity) {
        this.lineActivity = lineActivity;
    }
}
