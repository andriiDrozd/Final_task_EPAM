package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.CandidateDaoImplement;
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

public class ApplyCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameter.USER);

        int user_id = user.getUserId();
        String page = null;
        String facultyId = request.getParameter(Parameter.ID);
        int intFacultyId = Integer.parseInt(facultyId);

        if (user == null) {
            page = Path.PAGE__LOGIN;
            request.setAttribute(Parameter.LOGIN_ERROR, "Please Registry or Login to your account");
        } else if (user.getRole() == UserRole.CANDIDATE) {
            page = Path.PAGE__APPLY__FACULTY;
            if (!CandidateDaoImplement.areUserAppliedToTheFaculty(user_id, intFacultyId)) {
                page = Path.PAGE__APPLY__FACULTY;
            } else {
                request.setAttribute(Parameter.ERROR, "You already registered to this Faculty");
                page = Path.PAGE__VIEW__FACULTY;
            }
        }
        return page;
    }
}
