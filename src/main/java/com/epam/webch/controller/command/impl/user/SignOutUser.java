package com.epam.webch.controller.command.impl.user;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.model.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.webch.model.entity.user.User.UserRole.*;
import static com.epam.webch.model.entity.user.User.UserRole.admin;

public class SignOutUser implements Command {
    @AllowedRole({user,employee,admin})
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.HOME_PAGE);
        request.getSession().removeAttribute(SessionAttribute.CURRENT_USER_ROLE.name());
        request.getSession().removeAttribute(SessionAttribute.CURRENT_USER.name());
        request.getSession().setAttribute(SessionAttribute.CURRENT_USER_ROLE.name(), User.UserRole.guest);
        return new Router(PagePath.HOME_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
