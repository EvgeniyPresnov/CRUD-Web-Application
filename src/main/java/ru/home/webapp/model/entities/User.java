package ru.home.webapp.model.entities;

import java.io.Serializable;

/**
 * This is class is simple POJO containing get/set methods to store data retrieved using DAO class
 *
 * @author Evgeniy Presnov
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
