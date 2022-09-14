package com.example.final_task_epam.model.dao;

import org.apache.logging.log4j.LogManager;
import org.postgresql.jdbc2.optional.ConnectionPool;

import java.util.logging.Logger;

public abstract class AbstractDao {
    public Logger logger;

    {
        logger= (Logger) LogManager.getRootLogger();
    }
}
