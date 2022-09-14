package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.CandidateDaoImplement;
import com.example.final_task_epam.model.entity.Candidate;
import com.example.final_task_epam.model.entity.User;
import com.example.final_task_epam.role.UserRole;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class ViewAllCandidatesCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String page = null;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(Parameter.USER);

        Set<Candidate> candidates = null;
        if (user != null && user.getRole() == UserRole.ADMIN) {
            candidates = CandidateDaoImplement.getAllCandidates();
            for (Candidate c : candidates) {
                if (c.getFacultyState().equals("1")) {
                    c.setFacultyState("Open");
                } else if (c.getFacultyState().equals("2")) {
                    c.setFacultyState("Closed");
                }
            }

            if(candidates==null || candidates.isEmpty()){
                request.setAttribute(Parameter.ERROR,"You are not registered to any faculties");
            }

            request.setAttribute("candidates", candidates);
            page = Path.PAGE__VIEW__ALL__CANDIDATES;
        } else {
            candidates = CandidateDaoImplement.getCandidatesByUserID(Integer.parseInt(request.getParameter(Parameter.ID)));
            for (Candidate x: candidates) {
                System.out.println("candidate"+x.toString());
            }
            if(candidates==null || candidates.isEmpty()){
                request.setAttribute(Parameter.ERROR,"You are not registered to any faculties");
                System.out.println("not regestered");
            }
            for (Candidate c : candidates) {
                if (c.getFacultyState().equals("1")) {
                    c.setFacultyState("Open");
                } else if (c.getFacultyState().equals("2")) {
                    c.setFacultyState("Closed");
                    request.setAttribute(Parameter.FACULTY_STATE_ERROR, "If Faculty State is Closed, Please check the statement for further information");
                }
                request.setAttribute("candidates", candidates);
                page = Path.PAGE__VIEW__ALL__CANDIDATES;
            }

        }
        return page;
    }
}