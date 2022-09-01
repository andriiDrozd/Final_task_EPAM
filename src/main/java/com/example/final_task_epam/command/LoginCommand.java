package com.example.final_task_epam.command;


import com.example.final_task_epam.model.dao.implement.UserDaoImplement;
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

public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();
        String forward = null;

        String email = request.getParameter(Parameter.EMAIL);
        String password = request.getParameter(Parameter.PASSWORD);

        request.setAttribute(Parameter.EMAIL, email);
        session.setAttribute(Parameter.EMAIL, email);

        User user = UserDaoImplement.getByLogin(email);
        session.setAttribute(Parameter.USER, user);
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute(Parameter.LOGIN_ERROR, "One or more of the input boxes was blank. Try again");
            forward = Path.PAGE__LOGIN;
            return forward;
        }

        if (UserDaoImplement.existUser(email, password)) {
            if (user.getRole().equals(UserRole.ADMIN)) {
                forward = Path.PAGE__ADMIN__MENU;
            } else {
                forward = Path.PAGE__USER__MENU;
            }
        } else {
            session.setAttribute(Parameter.LOGIN_ERROR, "Please check your password and email, if you already registered");
            forward = Path.PAGE__LOGIN;
        }
        return forward;

    }
}
