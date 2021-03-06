package com.epam.webch.controller.command.impl.all;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.webch.model.entity.user.User.UserRole.*;
import static com.epam.webch.model.entity.user.User.UserRole.admin;

public class OpenErrorOperationPage implements Command {
    @AllowedRole
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.ERROR_OPERATION_PAGE.getValue());
        return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
