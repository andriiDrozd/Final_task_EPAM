package com.example.final_task_epam.model.dao.implement;

import com.example.final_task_epam.configuration.DbManager;
import com.example.final_task_epam.model.dao.AbstractDao;
import com.example.final_task_epam.model.dao.SubjectDao;
import com.example.final_task_epam.model.entity.Mark;
import com.example.final_task_epam.model.entity.Subject;
import com.example.final_task_epam.util.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImplement extends AbstractDao implements SubjectDao {

    private static final String FIND_SUBJECTS_BY_FACULTY_ID = "SELECT subject.id, subject.subject_name FROM faculty join required_subject on faculty.id=required_subject.faculty_id and faculty.id=? join subject on subject.id=required_subject.subject_id";
    private static final String INSERT_SUBJECT_GRADES = "INSERT INTO subject_marks (candidate_id, subject_id, mark) VALUES (?,?,?)";
    private static final String DELETE_GRADES_BY_APPLICANT_ID = "DELETE FROM subject_marks WHERE candidate_id=?";
    private static final String FIND_ALL_SUBJECTS = "SELECT subject.id, subject.subject_name FROM subject order by subject.id";
    private static final String FIND_SUBJECT_GRADE_BY_APPLICANT_ID = "SELECT subject.subject_name, subject_marks.mark " +
            "FROM subject_marks " +
            "JOIN subject ON subject.id=subject_marks.subject_id JOIN candidate ON subject_marks.candidate_id=candidate.id WHERE candidate.id=?";
    private static final String INSERT_SUBJECT = "INSERT INTO subject(subject_name) VALUES (?)";
    private static final String INSERT_REQUIRED_SUBJECTS = "INSERT INTO required_subject (faculty_id, subject_id) VALUES(?,?)";
    private static final String DELETE_SUBJECT_BY_ID = "DELETE FROM subject WHERE subject.id=?";
    private static final String DELETE_REQUIRED_SUBJECTS_BY_FACULTY_ID = "DELETE FROM required_subject WHERE faculty_id=?";


    public static List<Subject> getRequiredSubjects( int facultyId) {
Connection connection=null;
        try {
            connection=DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_SUBJECTS_BY_FACULTY_ID);
            preparedStatement.setInt(1, facultyId);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Subject> requiredSubjects = new ArrayList<>();

            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setName(resultSet.getString(Fields.SUBJECT_NAME));
                subject.setSubjectId(resultSet.getInt(Fields.ID));
                requiredSubjects.add(subject);
            }
            return requiredSubjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Subject> getSubjectMarksByCandidateId( int applicantId ) {
        Connection connection=null;
        try {
            connection=DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_SUBJECT_GRADE_BY_APPLICANT_ID);
            preparedStatement.setInt(1, applicantId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Subject> subjects = new ArrayList<>();
            Subject subject;
            while (resultSet.next()) {
                subject = new Subject();
                subject.setName(resultSet.getString(Fields.SUBJECT_NAME));
                subject.setMark(resultSet.getInt(Fields.MARK));
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteGradesByApplicantId(Connection connection, int applicantId) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GRADES_BY_APPLICANT_ID);
            preparedStatement.setInt(1, applicantId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static List<Subject> getAllSubjects() {

        Connection connection = null;
        try {
            connection=DbManager.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SUBJECTS);
            List<Subject> subjects = new ArrayList<>();
            Subject subject;
            while (resultSet.next()) {
                subject = new Subject();
                subject.setSubjectId(resultSet.getInt(Fields.ID));
                subject.setName(resultSet.getString(Fields.SUBJECT_NAME));
                subjects.add(subject);
            }
            System.out.println("list of subject"+subjects.toString());
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbManager.getInstance().commitAndClose(connection);
        }
    }


    public static void insertMarks(Connection connection, List<Mark> marks) {
        for (Mark mark : marks) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECT_GRADES);

                preparedStatement.setInt(1, mark.getCandidateId());
                preparedStatement.setInt(2, mark.getSubjectId());
                preparedStatement.setInt(3, Integer.parseInt(mark.getMark()));

                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }

    public static void insertRequiredSubjects(int facultyId, List<Subject> subjects) {
        Connection connection=null;
        for (Subject subject : subjects) {
            try {
                connection=DbManager.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUIRED_SUBJECTS);
                preparedStatement.setInt(1, facultyId);
                preparedStatement.setInt(2, subject.getSubjectId());
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }

    public static boolean insert(Subject subject) {
        Connection connection = null;
        try {
            connection=DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECT);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            DbManager.getInstance().commitAndClose(connection);
        }
    }

    public boolean delete(int subjectId) {
        Connection connection = null;
        try {
            connection=DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBJECT_BY_ID);
            preparedStatement.setInt(1, subjectId);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            DbManager.getInstance().commitAndClose(connection);
        }
    }

    public void deleteRequiredSubjectsByFacultyId(Connection connection, int facultyId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUIRED_SUBJECTS_BY_FACULTY_ID);
            preparedStatement.setInt(1, facultyId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
