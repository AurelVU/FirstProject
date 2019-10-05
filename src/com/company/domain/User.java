package com.company.domain;

public class User extends AbstractUser //Пользователь
{
    private int rating;
    private String aboutEmployee;
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
