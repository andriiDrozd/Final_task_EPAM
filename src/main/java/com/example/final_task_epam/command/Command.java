package com.example.final_task_epam.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;


public abstract class Command implements Serializable {
    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, SQLException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}