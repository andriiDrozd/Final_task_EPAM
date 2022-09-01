package com.example.final_task_epam.model.entity;

import com.example.final_task_epam.role.UserRole;

public class User {

    private int userId;
    private UserRole role;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String region;
    private String city;
    private String institute;

    public User() {
    }

    public User(UserRole role, String email, String password, String name, String surname, String region, String city, String institute) {

        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.region = region;
        this.city = city;
        this.institute = institute;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", institute='" + institute + '\'' +
                '}';
    }
}
