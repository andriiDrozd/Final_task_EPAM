package com.example.final_task_epam.command;


import com.example.final_task_epam.Messages;
import com.example.final_task_epam.model.dao.implement.UserDaoImplement;
import com.example.final_task_epam.model.entity.User;
import com.example.final_task_epam.role.UserRole;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.PasswordHash;
import com.example.final_task_epam.util.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.sql.SQLException;

public class LoginCommand extends Command {
    private static final Logger LOGGER=LogManager.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        LOGGER.info("entry");
        HttpSession session = request.getSession();
        String forward = null;

        String email = request.getParameter(Parameter.EMAIL);
        String password = request.getParameter(Parameter.PASSWORD);


//        request.setAttribute(Parameter.EMAIL, email);
//        session.setAttribute(Parameter.EMAIL, email);

        User user = UserDaoImplement.getByLogin(email);
        session.setAttribute(Parameter.USER, user);
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute(Parameter.LOGIN_ERROR, Messages.BOX_WAS_BLANK);
            forward = Path.PAGE__LOGIN;
            return forward;
        } else {
            String hashPassword= PasswordHash.hash(password);
            if (UserDaoImplement.existUser(email, hashPassword)) {
                if (user.getRole().equals(UserRole.ADMIN)) {
                    LOGGER.debug("successful login as "+user.getRole());
                    forward = Path.PAGE__ADMIN__MENU;

                } else {
                    LOGGER.debug("successful login as "+user.getRole());
                    forward = Path.PAGE__USER__MENU;
                }
            } else {
                request.setAttribute(Parameter.LOGIN_ERROR, Messages.CHECK_OR_REGISTRY);
                forward = Path.PAGE__LOGIN;
                LOGGER.info("error entry");
            }
        }
        return forward;

    }
}
