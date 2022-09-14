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

public class UserFilterJsp implements Filter {

    private List<String> accessCommands;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String[] commands = filterConfig.getInitParameter("UserFilterJsp").split(" ");
        accessCommands = Arrays.asList(commands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        System.out.println("URI=" + uri);

        User user = (User) session.getAttribute(Parameter.USER);
        if (user == null) {
            if (accessCommands.contains(uri)) {
                request.getSession().setAttribute(Parameter.ACCESS_ERROR, Messages.NO_ACCESS);
                response.sendRedirect(Path.ERROR_404); // No logged-in user found, so redirect to login page.
            }else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

        System.out.println("JSP Filter destroy");
    }
}
