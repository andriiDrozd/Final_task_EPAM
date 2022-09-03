package com.example.final_task_epam.command;

import com.example.final_task_epam.model.dao.implement.UserDaoImplement;
import com.example.final_task_epam.model.entity.User;
import com.example.final_task_epam.role.UserRole;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.PasswordHash;
import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class RegistryUserCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session = request.getSession();

        String email = request.getParameter(Parameter.EMAIL);
        String name = request.getParameter(Parameter.NAME);
        String surname = request.getParameter(Parameter.SURNAME);
        String city = request.getParameter(Parameter.CITY);
        String region = request.getParameter(Parameter.REGION);
        String eductionInstitution = request.getParameter(Parameter.INSTITUTE);
        String password = request.getParameter(Parameter.PASSWORD);
        String confirmPassword = request.getParameter(Parameter.CONFIRM_PASSWORD);

        request.setAttribute(Parameter.EMAIL, email);
        request.setAttribute(Parameter.NAME, name);
        request.setAttribute(Parameter.SURNAME, surname);
        request.setAttribute(Parameter.CITY, city);
        request.setAttribute(Parameter.REGION, region);
        request.setAttribute(Parameter.INSTITUTE, eductionInstitution);

//        session.setAttribute(Parameter.EMAIL, email);
//
//        request.setAttribute(Parameter.PASSWORD, password);

        String forward = null;

        if (email == null || name == null || surname == null || region == null || city == null || eductionInstitution == null ||
                password == null || confirmPassword == null) {
            request.setAttribute(Parameter.ERROR, "One or more of the input boxes was blank. Try again");
            forward = Path.PAGE__REGISTRATION;
            return forward;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute(Parameter.ERROR, "Please check your password");
            forward = Path.PAGE__REGISTRATION;
            return forward;
        }

        if (UserDaoImplement.existUser(email)) {
            request.setAttribute(Parameter.ERROR, "User with this email is already exist");
            forward = Path.PAGE__REGISTRATION;
        } else {
            String hashPassword = PasswordHash.hash(password);
            User user = new User(UserRole.CANDIDATE, email, hashPassword, name, surname, region, city, eductionInstitution);
            session.setAttribute(Parameter.USER, user);
            int userId = UserDaoImplement.insert(user);
            user.setUserId(userId);
            return forward = Path.PAGE__USER__MENU;
        }
        return forward;


    }
}
