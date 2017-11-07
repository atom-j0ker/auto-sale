package com.auto.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long id;

    @Embedded
    @Column(name = "username")
    private UserEmbeddable userEmbeddable;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "announcement")
    private String announcement;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Collection<Auto> auto = new ArrayList<Auto>();

    public Users(UserEmbeddable userEmbeddable, String role, String email, String firstName, String lastName, String phone, String announcement) {
        this.userEmbeddable = userEmbeddable;
        this.role = role;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.announcement = announcement;
    }

    public Users() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEmbeddable getUserEmbeddable() {
        return userEmbeddable;
    }

    public void setUserEmbeddable(UserEmbeddable userEmbeddable) {
        this.userEmbeddable = userEmbeddable;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public Collection<Auto> getAuto() {
        return auto;
    }

    public void setAuto(Collection<Auto> auto) {
        this.auto = auto;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", announcement='" + announcement + '\'' +
                '}';
    }
}
