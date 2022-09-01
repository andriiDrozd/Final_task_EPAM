package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.SubjectDaoImplement;
import com.example.final_task_epam.model.entity.Subject;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ViewSubjectCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String page = Path.PAGE__VIEW__SUBJECT;

            List<Subject> subjects = SubjectDaoImplement.getAllSubjects();
            if (subjects != null) {

                request.setAttribute(Parameter.SUBJECTS, subjects);
            } else {

            }
        return page;
    }
}
