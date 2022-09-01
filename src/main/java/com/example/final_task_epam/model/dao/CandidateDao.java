package com.example.final_task_epam.model.dao;

import com.example.final_task_epam.model.entity.Candidate;
import com.example.final_task_epam.model.entity.Faculty;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

public interface CandidateDao {
    static TreeSet<Candidate> getAllCandidates(Optional<Integer> enrollmentId) {
        return null;
    }
    void updateEnrolledCandidatesState(Connection connection, List<Integer> idList);
    void updateNotEnrolledCandidatesState(Connection connection);

    Map<Faculty, TreeSet<Candidate>> getCurrentEnrollmentCandidates(Connection connection);
    List<Candidate> getCandidatesByUserId(int userId);
    List<Candidate> getCandidatesByUserId(Connection connection, int userId);

    static List<Candidate> getCandidatesIdByFacultyId(int facultyId) {
        return null;
    }

    static Candidate getCandidateById(int candidateId) {
        return null;
    }
    void deleteCandidateById(Connection connection, int candidateId);

    static int insert(Connection connection, Candidate candidate) {
        return 0;
    }

}
