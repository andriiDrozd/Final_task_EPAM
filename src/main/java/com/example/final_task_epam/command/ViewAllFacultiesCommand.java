package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.FacultyDaoImplement;
import com.example.final_task_epam.model.entity.Faculty;
import com.example.final_task_epam.util.Fields;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class ViewAllFacultiesCommand extends Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String page = null;

        List<Faculty> faculties = FacultyDaoImplement.getAllFaculties();

        if (!faculties.isEmpty()) {
            if(request.getParameter("type")!=null) {

                page = Path.PAGE__VIEW__ALL__FACULTIES;
                if ( request.getParameter("type").equals("1")) {

                    faculties = FacultyDaoImplement.getAllFacultiesOrderByName();
                    request.setAttribute(Parameter.LIST_OF_FACULTIES, faculties);

                } else if (request.getParameter("type").equals("2")) {

                    faculties = FacultyDaoImplement.getAllFacultiesOrderByNameDesc();
                    request.setAttribute(Parameter.LIST_OF_FACULTIES, faculties);

                } else if (request.getParameter("type").equals("3")) {

                    faculties = FacultyDaoImplement.getAllFacultiesOrderByCapacity();
                    request.setAttribute(Parameter.LIST_OF_FACULTIES, faculties);

                } else if (request.getParameter("type").equals("4")) {

                    faculties = FacultyDaoImplement.getAllFacultiesOrderByBudgetPlaces();
                    request.setAttribute(Parameter.LIST_OF_FACULTIES, faculties);

                }
            }else{
                faculties = FacultyDaoImplement.getAllFaculties();
                request.setAttribute(Parameter.LIST_OF_FACULTIES, faculties);
                page = Path.PAGE__VIEW__ALL__FACULTIES;
            }
        } else {

            page = Path.PAGE__VIEW__ALL__FACULTIES;
            request.setAttribute(Parameter.ERROR, "No any faculties avaliable now(");
        }
        return page;
    }
}

