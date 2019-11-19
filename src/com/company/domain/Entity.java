package com.company.domain;

import com.company.domain.annotations.SQLinformationVariable;

public abstract class Entity {
    @SQLinformationVariable(name = "id", SQLtype = "INT(11)", SQLparams = "PRIMARY KEY NOT NULL AUTO_INCREMENT")
    protected Long id;

    public Entity() {
    }

    public Long  getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
