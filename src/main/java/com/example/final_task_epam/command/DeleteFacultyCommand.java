package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.FacultyDaoImplement;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DeleteFacultyCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String page = null;
        int facultyId= Integer.parseInt(request.getParameter(Parameter.FACULTY_ID));
        FacultyDaoImplement.deleteFaculty(facultyId);

page= Path.PAGE__VIEW__ALL__FACULTIES;
        return page;
    }
}
