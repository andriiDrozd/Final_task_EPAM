package com.example.final_task_epam.model.entity;

public class Subject {

    private int subjectId;
    private String name;
    private int mark;

    public Subject() {
    }

    public Subject(int subjectId, String name, int grade) {
        this.subjectId = subjectId;
        this.name = name;
        this.mark = grade;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int grade) {
        this.mark = grade;
    }
}
