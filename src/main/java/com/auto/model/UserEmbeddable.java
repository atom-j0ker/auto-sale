package com.auto.model;
import javax.persistence.*;

@Embeddable
public class UserEmbeddable {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public UserEmbeddable(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEmbeddable() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEmbeddable{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}