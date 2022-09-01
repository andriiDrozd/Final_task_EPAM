package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.CandidateDaoImplement;
import com.example.final_task_epam.model.entity.Candidate;
import com.example.final_task_epam.model.entity.User;
import com.example.final_task_epam.model.state.CandidateState;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ApplyToFacultyCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(Parameter.USER);

        String page = null;
        String facultyId = request.getParameter(Parameter.ID);
        int intFacultyId = Integer.parseInt(facultyId);

        String[] subjectMarksStrings = request.getParameterValues(Parameter.GRADE);

        Candidate newCandidate = new Candidate();

        newCandidate.setUserId(user.getUserId());
        newCandidate.setFacultyId(intFacultyId);
        newCandidate.setCandidateState(CandidateState.APPLIED);
        CandidateDaoImplement.addApplicant(newCandidate, subjectMarksStrings);

        page = Path.PAGE__USER__MENU;


        return page;

    }
}
