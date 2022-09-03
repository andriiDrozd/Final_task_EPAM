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

public class ViewFacultyCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String page = null;

//        int facultyId = Integer.parseInt(request.getParameter(Parameter.ID));
//
//        try {
//            Faculty faculty = FacultyDaoImplement.getById(facultyId);
//            if(faculty!=null) {
////                LOGGER.info("faculty and required subjects were found and set as attribute");
////                request.setAttribute(Parameters.FACULTY, faculty);
//                page = Path.
//            }else{
//                LOGGER.warn("faculty wasn't found");
//            }
//        } catch (FacultyServiceException e) {
//            LOGGER.error(e.getMessage());
//            request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
//        }
page= Path.PAGE__VIEW__FACULTY;
        return page;
    }
}
