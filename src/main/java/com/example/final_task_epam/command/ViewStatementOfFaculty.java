package com.example.final_task_epam.command;

import com.example.final_task_epam.Messages;
import com.example.final_task_epam.model.dao.implement.CandidateDaoImplement;
import com.example.final_task_epam.model.dao.implement.FacultyDaoImplement;
import com.example.final_task_epam.model.dao.implement.UserDaoImplement;
import com.example.final_task_epam.model.entity.Candidate;
import com.example.final_task_epam.model.entity.Faculty;
import com.example.final_task_epam.model.entity.User;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.TreeSet;

public class ViewStatementOfFaculty extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        String page = null;
        int facultyId = Integer.parseInt(request.getParameter(Parameter.FACULTY_ID));
        TreeSet<Candidate> candidates = CandidateDaoImplement.selectEnrolledCandidatesByFacultyId(facultyId);
        request.setAttribute(Parameter.CANDIDATES, candidates);
        page = Path.PAGE__VIEW__CANDIDATES__STATEMENT;
        if (candidates==null) {
            request.setAttribute("faculty_closed", Messages.NO_ANY_CANDIDATES);
        }else{

            Faculty faculty = FacultyDaoImplement.getById(facultyId);
            String facultyName = faculty.getName();
            System.out.println(facultyName);

            int capacity = faculty.getCapacity();
            int budgetPlaces = faculty.getBudgetPlaces();
//            String email = (String) request.getSession().getAttribute(Parameter.EMAIL);
//            User user = UserDaoImplement.getByLogin(email);
            User user= (User) session.getAttribute(Parameter.USER);
            System.out.println("User:"+user.toString());
            int userId = user.getUserId();
            Candidate candidate = CandidateDaoImplement.getCandidateByFacultyNameAndUserId(facultyName, userId);
            if (candidate != null) {
                int candidateId = candidate.getId();
                System.out.println(candidateId);

                for (Candidate c : candidates) {
                    if (c.getFacultyState().equals("2")) {
                        if (c.getId() == candidateId) {
                            if (c.getRating_position() <= budgetPlaces) {
                                request.setAttribute("faculty_closed", "You are enrolled to Budget");
                            } else if (c.getRating_position() > budgetPlaces && c.getRating_position() <= capacity) {
                                request.setAttribute("faculty_closed", "You are enrolled to Contract");
                            } else {
                                request.setAttribute("faculty_closed", "Unfortunately, you are not enrolled to this Faculty((");
                            }
                        }

                    }
                }
            } else if (candidate == null) {
                request.setAttribute("faculty_closed", "You are not registered to this Faculty");
            }
        }
        return page;
    }
}
