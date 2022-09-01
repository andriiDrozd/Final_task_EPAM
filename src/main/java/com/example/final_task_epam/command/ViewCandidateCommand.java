package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.CandidateDaoImplement;
import com.example.final_task_epam.model.entity.Candidate;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ViewCandidateCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

        String page = null;
        int candidateId = Integer.parseInt(request.getParameter(Parameter.ID));


        Candidate candidate = CandidateDaoImplement.getCandidateById(candidateId);
        if (candidate != null) {
            request.setAttribute(Parameter.CANDIDATE, candidate);
            request.setAttribute(Parameter.SUBJECTS, candidate.getSubjects());
        } else {

        }
        page = Path.PAGE__VIEW__CANDIDATE;

        return page;
    }
}
