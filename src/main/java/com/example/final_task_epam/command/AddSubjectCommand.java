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

public class AddSubjectCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        System.out.println("add object");
        String page = Path.PAGE__VIEW__SUBJECT;
        String subjectName = request.getParameter(Parameter.SUBJECT_NAME);

            Subject subject = new Subject();
            subject.setName(subjectName);
        SubjectDaoImplement.insert(subject);

        return page;
    }
}
