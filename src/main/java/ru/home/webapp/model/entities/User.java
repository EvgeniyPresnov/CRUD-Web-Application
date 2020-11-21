package ru.home.webapp.model.entities;

import java.io.Serializable;

/**
 * Java Bean
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
