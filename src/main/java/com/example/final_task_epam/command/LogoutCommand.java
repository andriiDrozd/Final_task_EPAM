package com.example.final_task_epam.command;

import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LogoutCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        System.out.println("Enter logout");
        String page = "/jsp/welcome.jsp";
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute("user");
            System.out.println("logout");
            page = Path.PAGE__LOGIN;
        }
        return page;
    }
}

