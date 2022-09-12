package com.example.final_task_epam.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class JspAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        System.out.println("JspAccessFilter");
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("/jsp/login.jsp"); // No logged-in user found, so redirect to login page.
        } else {
            filterChain.doFilter(servletRequest, servletResponse); // Logged-in user found, so just continue request.
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
