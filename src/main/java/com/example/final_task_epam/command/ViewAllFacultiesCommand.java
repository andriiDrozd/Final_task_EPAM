package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.FacultyDaoImplement;
import com.example.final_task_epam.model.entity.Faculty;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ViewAllFacultiesCommand extends Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String page=null;

            List<Faculty> faculties = FacultyDaoImplement.getAllFaculties();
            if(!faculties.isEmpty()) {

                page= Path.PAGE__VIEW__ALL__FACULTIES;
            }else{

                page=Path.PAGE__VIEW__ALL__FACULTIES;
                request.setAttribute(Parameter.ERROR, "No any faculties avaliable now(");
            }

        return page;
    }
}
