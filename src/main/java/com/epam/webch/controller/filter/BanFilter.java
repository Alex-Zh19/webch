package com.epam.webch.controller.filter;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.model.entity.user.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class BanFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getSession().getAttribute(SessionAttribute.CURRENT_USER_ROLE.name()) != User.UserRole.guest) {
            User currentUser = (User) request.getSession().getAttribute(SessionAttribute.CURRENT_USER.name());
            if (currentUser.getUserStatus() != User.UserStatus.active) {
                request.getRequestDispatcher(PagePath.BAN_PAGE.getValue()).forward(request, response);
            }
        }
        filterChain.doFilter(request, response);
    }
}
