package com.example.final_task_epam.configuration;

import java.util.ResourceBundle;

public class ConfigurationDB {

    public static final String DRIVER_PATH = "driver_path";
    public static final String DATABASE_LOGIN = "database_login";
    public static final String DATABASE_PASSWORD = "database_password";
    public static final String DATABASE_CONNECTION_PATH = "database_path";

    private static final ResourceBundle resourceBundle;
    private static final String CONFIGURATION_PROPERTIES = "configurationDB";

    static {
        resourceBundle = ResourceBundle.getBundle(CONFIGURATION_PROPERTIES);
    }

    public static String getString(String key) {
        if (key != null) {
            return resourceBundle.getString(key);
        }
        return null;
    }
}
