package com.example.final_task_epam.command;

import com.example.final_task_epam.Messages;
import com.example.final_task_epam.model.dao.implement.CandidateDaoImplement;
import com.example.final_task_epam.model.dao.implement.FacultyDaoImplement;
import com.example.final_task_epam.model.dao.implement.UserDaoImplement;
import com.example.final_task_epam.model.entity.Candidate;
import com.example.final_task_epam.model.entity.Faculty;
import com.example.final_task_epam.model.entity.User;
import com.example.final_task_epam.role.UserRole;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;
import com.sun.source.tree.Tree;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeSet;

public class ViewStatementOfFaculty extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        String page = null;
        int facultyId = Integer.parseInt(request.getParameter(Parameter.FACULTY_ID));
        TreeSet<Candidate> candidates = CandidateDaoImplement.selectEnrolledCandidatesByFacultyId(facultyId);
        User user= (User) session.getAttribute(Parameter.USER);

        page = Path.PAGE__VIEW__CANDIDATES__STATEMENT;
        if( user==null || user.getRole().equals(UserRole.ADMIN)) {
            candidates=  CandidateDaoImplement.selectCandidatesByFacultyId(facultyId);

        } else {

            if (candidates == null) {
                request.setAttribute(Parameter.FACULTY_CLOSED, Messages.NO_ANY_CANDIDATES);
            } else {
                int userId = user.getUserId();
                Faculty faculty = FacultyDaoImplement.getById(facultyId);
                String facultyName = faculty.getName();

                int capacity = faculty.getCapacity();
                int budgetPlaces = faculty.getBudgetPlaces();


                Candidate candidate = CandidateDaoImplement.getCandidateByFacultyNameAndUserId(facultyName, userId);
                if (candidate != null) {
                    int candidateId = candidate.getId();

                    for (Candidate c : candidates) {
                        if (c.getFacultyState().equals("2")) {
                            if (c.getId() == candidateId) {
                                if (c.getRating_position() <= budgetPlaces) {
                                    request.setAttribute(Parameter.FACULTY_CLOSED, Messages.ENROLLED_TO_BUDGET);
                                } else if (c.getRating_position() > budgetPlaces && c.getRating_position() <= capacity) {
                                    request.setAttribute(Parameter.FACULTY_CLOSED, Messages.ENROLLED_TO_CONTRACT);
                                } else {
                                    request.setAttribute(Parameter.FACULTY_CLOSED, Messages.NOT_ENROLLED_TO_FACULTY);
                                }
                            }

                        }
                    }
                } else if (candidate == null) {
                    request.setAttribute(Parameter.FACULTY_CLOSED, Messages.NOT_REGISTERED_TO_FACULTY);
                }
            }
        }
        request.setAttribute(Parameter.CANDIDATES, candidates);
        return page;
    }
}
