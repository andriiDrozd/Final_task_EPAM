package com.example.final_task_epam.filter;

import com.example.final_task_epam.Messages;
import com.example.final_task_epam.model.entity.User;
import com.example.final_task_epam.role.UserRole;
import com.example.final_task_epam.util.Parameter;
import com.example.final_task_epam.util.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class CommandAccessFilter implements Filter {

    private static final String DELIMITER = " ";

    private String contextPath;
    private List<String> userCommands;
    String exclusiveCommands;
    UserRole userRole;
    String logMessage;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        contextPath = filterConfig.getServletContext().getContextPath();
        String[] commands = filterConfig.getInitParameter(exclusiveCommands).split(DELIMITER);
        userCommands = Arrays.asList(commands);
//        for (String s: userCommands) {
//            System.out.println(s);
//        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter(Parameter.COMMAND);
        User user = (User) request.getSession().getAttribute(Parameter.USER);

        if ((user == null ||  user.getRole()!= userRole) && userCommands.contains(command)) {
//            LOGGER.debug(logMessage + command);
            System.out.println("Error");
            System.out.println(user.getRole()+"!="+userRole);


                    for (String s: userCommands) {
            System.out.println(s);
        }

            request.getSession().setAttribute(Parameter.ERROR, Messages.NO_ACCESS);
            response.sendRedirect(contextPath + "/" + Path.REDIRECT_ERROR_PAGE);
        } else {
            System.out.println("continue");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
