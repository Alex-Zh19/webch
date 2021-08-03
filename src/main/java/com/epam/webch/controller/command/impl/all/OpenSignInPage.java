package com.epam.webch.controller.command.impl.all;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.webch.model.entity.user.User.UserRole.*;
import static com.epam.webch.model.entity.user.User.UserRole.admin;

public class OpenSignInPage implements Command {
    @AllowedRole({guest,user,employee,admin})
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.SIGN_IN_PAGE);
        return new Router(PagePath.SIGN_IN_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
