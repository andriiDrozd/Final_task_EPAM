package com.example.final_task_epam.model.entity;

import java.util.List;

public class Faculty {

    private int id;
    private String name;
    private int capacity;
    private List<Subject> requiredSubjects;

    private int budgetPlaces;
    private int state;

    public Faculty() {
    }

    public Faculty(int id, String name, int capacity, List<Subject> requiredSubjects, int budgetPlaces, int state) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.requiredSubjects = requiredSubjects;
        this.budgetPlaces=budgetPlaces;
        this.state=state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBudgetPlaces() {
        return budgetPlaces;
    }

    public void setBudgetPlaces(int budgetPlaces) {
        this.budgetPlaces = budgetPlaces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Subject> getRequiredSubjects() {
        return requiredSubjects;
    }

    public void setRequiredSubjects(List<Subject> requiredSubjects) {
        this.requiredSubjects = requiredSubjects;
    }
}
