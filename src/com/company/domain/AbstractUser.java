package com.company.domain;

public abstract class AbstractUser extends Entity {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AbstractUser(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
