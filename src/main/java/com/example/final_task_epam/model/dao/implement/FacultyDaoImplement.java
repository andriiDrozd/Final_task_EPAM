package com.example.final_task_epam.model.dao.implement;

import com.example.final_task_epam.configuration.DbManager;
import com.example.final_task_epam.model.entity.Faculty;
import com.example.final_task_epam.model.dao.AbstractDao;
import com.example.final_task_epam.model.dao.FacultyDao;
import com.example.final_task_epam.util.Fields;
import com.example.final_task_epam.util.Parameter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyDaoImplement extends AbstractDao implements FacultyDao {
    private static final String FIND_ALL = "SELECT id, name, capacity, state FROM faculty";
    private static final String FIND_BY_ID = "SELECT id, name, capacity, budget_places,state FROM faculty WHERE id=?";
    private static final String INSERT_FACULTY = "INSERT INTO faculty (name, capacity, budget_places, state) VALUES (?,?,?,?)";
    private static final String DELETE_BY_ID = "DELETE FROM faculty WHERE id=?";

    private static final String CLOSE_FACULTY_BY_ID = "UPDATE faculty Set state=? WHERE faculty.id=?";

    public static void closeFaculty(int facultyId, int facultyState) {
        Connection connection = null;
        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CLOSE_FACULTY_BY_ID);
            preparedStatement.setInt(1, facultyState);
            preparedStatement.setInt(2, facultyId);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Faculty> getAllFaculties() {
        Connection connection = null;
        try {
            connection = DbManager.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);

            List<Faculty> faculties = new ArrayList<>();

            while (resultSet.next()) {
                Faculty faculty = new Faculty();
                faculty.setId(resultSet.getInt(Fields.ID));
                faculty.setName(resultSet.getString(Fields.NAME));
                faculty.setCapacity(resultSet.getInt(Fields.CAPACITY));
                faculty.setState(resultSet.getInt(Fields.STATE));
                faculties.add(faculty);
            }
            return faculties;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbManager.getInstance().commitAndClose(connection);
        }

    }

    public static Faculty getById(int facultyId) {
        Connection connection = null;
        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, facultyId);
            ResultSet resultSet = preparedStatement.executeQuery();

            Faculty faculty = null;
            while (resultSet.next()) {
                faculty = new Faculty();
                faculty.setId(resultSet.getInt(Fields.ID));
                faculty.setName(resultSet.getString(Fields.NAME));
                faculty.setCapacity(resultSet.getInt(Fields.CAPACITY));
                faculty.setBudgetPlaces(resultSet.getInt(Fields.BUDGET_PLACES));
                faculty.setState(resultSet.getInt(Parameter.STATE));
            }
            return faculty;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static int insert(Faculty faculty) {
        Connection connection = null;
        try {
            connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FACULTY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, faculty.getName());
            preparedStatement.setInt(2, faculty.getCapacity());
            preparedStatement.setInt(3, faculty.getBudgetPlaces());
            preparedStatement.setInt(4, faculty.getState());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean delete(Connection connection, int facultyId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, facultyId);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
