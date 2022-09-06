package com.example.final_task_epam.command;

import com.example.final_task_epam.util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ChooseLanguage extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        HttpSession session=request.getSession();

        String local=request.getParameter("local");
        System.out.println(local);
        session.setAttribute("local", local);

        return Path.PAGE__LOGIN;
    }
}
