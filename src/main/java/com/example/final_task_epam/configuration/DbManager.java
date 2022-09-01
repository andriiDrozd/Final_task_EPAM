package com.example.final_task_epam.configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    private static DbManager instance;

    public static synchronized DbManager getInstance() {
        if (instance == null)
            instance = new DbManager();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {

            Class.forName(ConfigurationDB.getString(ConfigurationDB.DRIVER_PATH));
            connection = DriverManager.getConnection(ConfigurationDB.getString(ConfigurationDB.DATABASE_CONNECTION_PATH),
                    ConfigurationDB.getString(ConfigurationDB.DATABASE_LOGIN), ConfigurationDB.getString(ConfigurationDB.DATABASE_PASSWORD));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    private DbManager() {
    }

    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
