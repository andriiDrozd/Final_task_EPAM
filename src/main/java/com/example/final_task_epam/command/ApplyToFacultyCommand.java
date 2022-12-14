package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.CandidateDaoImplement;
import com.example.final_task_epam.model.entity.Candidate;
import com.example.final_task_epam.model.entity.User;
import com.example.final_task_epam.model.state.CandidateState;
import com.example.final_task_epam.model.validator.MarksValidator;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.example.final_task_epam.model.validator.MarksValidator.validateMarks;

public class ApplyToFacultyCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(Parameter.USER);

        String page = null;
        String facultyId = request.getParameter(Parameter.ID);
        int intFacultyId = Integer.parseInt(facultyId);

        String[] subjectMarksStrings = request.getParameterValues(Parameter.GRADE);
        if(MarksValidator.validateMarks(subjectMarksStrings)) {
            Candidate newCandidate = new Candidate();

            newCandidate.setUserId(user.getUserId());
            newCandidate.setFacultyId(intFacultyId);
            newCandidate.setCandidateState(CandidateState.APPLIED);
            CandidateDaoImplement.addApplicant(newCandidate, subjectMarksStrings);
            page = Path.PAGE__USER__MENU;
        } else{
            session.setAttribute("error.marks","The mark must be in range 1-100");
            page=Path.PAGE__APPLY__FACULTY;
        }

//todo: change setAttribute - to setSessionAttribute

        return page;

    }
}
