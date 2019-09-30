package com.company.domain;

public class User extends AbstractDomain //Пользователь
{
    private int rating;
    private String aboutEmployee;
    private String lineActivity;

    public User(int rating, String aboutEmployee, String lineActivity) {
        this.rating = rating;
        this.aboutEmployee = aboutEmployee;
        this.lineActivity = lineActivity;
    }

    public User() {
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
