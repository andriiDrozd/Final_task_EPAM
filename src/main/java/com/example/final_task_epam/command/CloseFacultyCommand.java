package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.FacultyDaoImplement;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CloseFacultyCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String page=null;
        int facultyState = Integer.parseInt(request.getParameter(Parameter.STATE));
        int facultyId= Integer.parseInt(request.getParameter(Parameter.FACULTY_ID));
        System.out.println(facultyId);
        FacultyDaoImplement.closeFaculty(facultyId,facultyState);

            page = Path.PAGE__ADMIN__MENU;

        return page;
    }
}
