package com.company.domain;

import com.company.domain.annotations.SQLinformationVariable;

public abstract class AbstractUser extends Entity {
    @SQLinformationVariable(name = "login", SQLtype = "VARCHAR(100)", SQLparams = "NOT NULL")
    private String login;
    @SQLinformationVariable(name = "password", SQLtype = "VARCHAR(100)", SQLparams = "NOT NULL")
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
