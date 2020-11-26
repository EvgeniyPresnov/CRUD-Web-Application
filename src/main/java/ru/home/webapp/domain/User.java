package ru.home.webapp.domain;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;

        return Objects.equals(name, other.name) && Objects.equals(password, other.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
