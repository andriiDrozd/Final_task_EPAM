package com.example.final_task_epam.controller;

import com.example.final_task_epam.command.Command;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ControllerServlet", urlPatterns = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String commandName = request.getParameter("command");
//        // obtain command object by its name
//        Command command = CommandContainer.get(commandName);
//
//        // execute command and get forward address
//        String forward = null;
//        try {
//            forward = command.execute(request, response);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        // if the forward address is not null go to the address
//        if (forward != null) {
//            getServletContext().getRequestDispatcher(forward).forward(request, response);
//        }
        this.doPost(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // extract command name from the request
        String commandName = request.getParameter("command");
        System.out.println("command"+commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);

        // execute command and get forward address
        String forward = null;


        try {
            forward = command.execute(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // if the forward address is not null go to the address
        if (forward != null) {
            getServletContext().getRequestDispatcher(forward).forward(request, response);
        }
    }

}
