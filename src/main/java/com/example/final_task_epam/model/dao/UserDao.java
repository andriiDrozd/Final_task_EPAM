package com.example.final_task_epam.model.dao;

import com.example.final_task_epam.model.entity.User;

import java.sql.Connection;

public interface UserDao {

    static User getByLogin(String login) {
        return null;
    }

    User getByLogin(Connection connection, String login);

    static int insert(Connection connection, User user) {
        return 0;
    }

}
