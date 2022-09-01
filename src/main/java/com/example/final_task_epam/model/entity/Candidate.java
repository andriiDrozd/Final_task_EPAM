package com.example.final_task_epam.model.entity;

import com.example.final_task_epam.model.state.CandidateState;
import com.example.final_task_epam.role.UserRole;

import java.util.List;

public class Candidate extends User implements Comparable<Candidate> {

    private int id;
    private int facultyId;
    private String facultyName;
    private CandidateState candidateState;
    private int totalRating;
    private List<Subject> subjects;

    private String facultyState;
    private int rating_position;

    public Candidate() {

    }

    public Candidate(UserRole role, String email, String password, String name, String surname, String region, String city, String institute, int id, int facultyId, String facultyName, CandidateState candidateState, int totalRating, List<Subject> subjects, String facultyState, int rating_position) {
        super(role, email, password, name, surname, region, city, institute);
        this.id = id;
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.candidateState = candidateState;
        this.totalRating = totalRating;
        this.subjects = subjects;
        this.facultyState=facultyState;
        this.rating_position=rating_position;
    }

    public int getRating_position() {
        return rating_position;
    }

    public void setRating_position(int rating_position) {
        this.rating_position = rating_position;
    }

    public String getFacultyState() {
        return facultyState;
    }

    public void setFacultyState(String facultyState) {
        this.facultyState = facultyState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public CandidateState getCandidateState() {
        return candidateState;
    }

    public void setCandidateState(CandidateState candidateState) {
        this.candidateState = candidateState;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public int compareTo(Candidate candidate) {
//        return Integer.compare(applicant.totalRating, totalRating);
        return candidate.totalRating != totalRating ? Integer.compare(candidate.totalRating, totalRating) : Integer.compare(id, candidate.id);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", facultyId=" + facultyId +
                ", facultyName='" + facultyName + '\'' +
                ", candidateState=" + candidateState +
                ", totalRating=" + totalRating +
                ", subjects=" + subjects +
                '}';
    }
}
