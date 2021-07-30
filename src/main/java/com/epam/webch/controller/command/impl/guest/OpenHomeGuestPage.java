package com.epam.webch.controller.command.impl.guest;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.model.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OpenHomeGuestPage implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.HOME_PAGE);
        request.getSession().setAttribute(SessionAttribute.CURRENT_USER_ROLE.name(), User.UserRole.guest);
        return new Router(PagePath.HOME_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
