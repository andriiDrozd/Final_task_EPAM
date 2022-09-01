package com.example.final_task_epam.model.entity;

public class Mark {

    private String mark;
    private int subjectId;
    private int candidateId;

    public Mark() {
    }

    public Mark(String mark, int subjectId, int candidateId) {
        this.mark = mark;
        this.subjectId = subjectId;
        this.candidateId = candidateId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }
}
