package com.epam.webch.controller.filter;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.model.entity.user.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/employee/*")
public class EmployeeAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User.UserRole role = (User.UserRole) request.getSession().getAttribute(SessionAttribute.CURRENT_USER_ROLE.name());
        if(role== User.UserRole.guest||role== User.UserRole.user){
            request.getRequestDispatcher(PagePath.DENIED_ACCESS_PAGE.getValue()).forward(request,response);
        }else{
            filterChain.doFilter(request,response);
        }
    }
}
