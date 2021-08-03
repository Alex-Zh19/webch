package com.epam.webch.controller.filter;

import com.epam.webch.controller.AllowedRoleChecker;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.CommandFactory;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class AccessFilter implements Filter {
    private static final String COMMAND = "command";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String commandFromPage = request.getParameter(COMMAND);
        if (commandFromPage == null) {
            filterChain.doFilter(request, response);
        }
        Command command = CommandFactory.createCommand(commandFromPage);
        if (AllowedRoleChecker.isRoleAllowed(command, request)) {
            filterChain.doFilter(request, response);
        }
        request.getRequestDispatcher(PagePath.DENIED_ACCESS_PAGE.getValue()).forward(request, response);
    }
}
