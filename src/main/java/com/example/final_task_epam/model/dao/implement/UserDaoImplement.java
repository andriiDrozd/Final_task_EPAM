package com.example.final_task_epam.model.dao.implement;

import com.example.final_task_epam.configuration.DbManager;
import com.example.final_task_epam.model.dao.AbstractDao;
import com.example.final_task_epam.model.dao.UserDao;
import com.example.final_task_epam.model.entity.User;
import com.example.final_task_epam.role.UserRole;
import com.example.final_task_epam.util.Fields;

import java.sql.*;

public class UserDaoImplement extends AbstractDao implements UserDao {
    private static final String FIND_BY_LOGIN = "SELECT user_table.id, email, password, name, surname,region, city, institute, role_type FROM user_table JOIN user_role ON user_table.user_role_id=user_role.id WHERE email=?";
    private static final String INSERT_USER = "INSERT INTO user_table(user_role_id, email, password, name, surname, region, city, institute) VALUES (?, ?, ?, ?, ?, ?,?,?)";
    private static final String EXIST_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM user_table WHERE email=? AND password=?";
    private static final String EXIST_USER_BY_EMAIL = "SELECT * FROM user_table WHERE email=? ";


    public static User getByLogin(String login) {
        Connection connection = null;
        try {
            connection = DbManager.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            return findByLogin(connection, login);
        } finally {
            DbManager.getInstance().commitAndClose(connection);
        }
    }

    public User getByLogin(Connection connection, String login) {
        return findByLogin(connection, login);
    }

    public static boolean existUser(String email, String password) {
        try {
            Connection connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EXIST_USER_BY_EMAIL_AND_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean existUser(String email) {
        try {
            Connection connection = DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EXIST_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private static User findByLogin(Connection connection, String login) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt(Fields.ID));
                user.setEmail(resultSet.getString(Fields.EMAIL));
                user.setPassword(resultSet.getString(Fields.PASSWORD));
                user.setName(resultSet.getString(Fields.NAME));
                user.setSurname(resultSet.getString(Fields.SURNAME));
                user.setRegion(resultSet.getString(Fields.REGION));
                user.setCity(resultSet.getString(Fields.CITY));
                user.setInstitute(resultSet.getString(Fields.INSTITUTE));
                user.setRole(UserRole.valueOf(resultSet.getString(Fields.ROLE_TYPE).toUpperCase()));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int insert( User user) {
        Connection connection=null;
        int generatedId = 0;
        try {
            connection=DbManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getRole().getOrdinalNumber());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSurname());
            preparedStatement.setString(6, user.getRegion());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getInstitute());

            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generatedId;
    }

    public boolean update(User user) {
//        Connection connection = pool.getConnection();
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
//            preparedStatement.setString(1, user.getLastName());
//            preparedStatement.setString(2, user.getFirstName());
//            preparedStatement.setString(3, user.getPatronymic());
//            preparedStatement.setInt(4, user.getUserId());
//            preparedStatement.execute();
//            return true;
//        } catch (SQLException e) {
//            throw new UserDaoException("could not update user: " + e.getMessage());
//        } finally {
//            pool.releaseConnection(connection);
//        }
        return true;
    }
}
