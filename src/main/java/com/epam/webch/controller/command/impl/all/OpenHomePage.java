package com.epam.webch.controller.command.impl.all;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.model.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.webch.model.entity.user.User.UserRole.*;
import static com.epam.webch.model.entity.user.User.UserRole.admin;

public class OpenHomePage implements Command {

    @AllowedRole({guest,user,employee,admin})
    @Override
    public Router execute(HttpServletRequest request) {
        Router result;
        User.UserRole role = (User.UserRole) request.getSession().getAttribute(SessionAttribute.CURRENT_USER_ROLE.name());
        if (role == User.UserRole.guest) {
            request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.HOME_PAGE);
            result = new Router(PagePath.HOME_PAGE.getValue(), Router.RouterType.FORWARD);
        } else if (role == User.UserRole.user) {
            request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.HOME_USER_PAGE);
            result = new Router(PagePath.HOME_USER_PAGE.getValue(), Router.RouterType.FORWARD);
        } else if (role == User.UserRole.admin) {
            request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.HOME_ADMIN_PAGE);
            result = new Router(PagePath.HOME_ADMIN_PAGE.getValue(), Router.RouterType.FORWARD);
        } else {
            request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.HOME_EMPLOYEE_PAGE);
            result = new Router(PagePath.HOME_EMPLOYEE_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return result;
    }
}
