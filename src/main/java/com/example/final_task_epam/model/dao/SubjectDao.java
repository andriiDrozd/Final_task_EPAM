package com.example.final_task_epam.model.dao;

import com.example.final_task_epam.model.entity.Mark;
import com.example.final_task_epam.model.entity.Subject;

import java.sql.Connection;
import java.util.List;

public interface SubjectDao {
    static List<Subject> getAllSubjects() {
        return null;
    }


    static List<Subject> getRequiredSubjects(Connection connection, int facultyId) {
        return null;
    }

    static List<Subject> getSubjectMarksByApplicantId(Connection connection, int applicantId) {
        return null;
    }


    static void insertMarks(Connection connection, List<Mark> grades) {

    }

    static boolean insert(Subject subject) {
        return false;
    }

}
