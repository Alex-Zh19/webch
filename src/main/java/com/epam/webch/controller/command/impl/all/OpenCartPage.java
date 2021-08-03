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

public class OpenCartPage implements Command {
    @AllowedRole({guest,user,employee,admin})
    @Override
    public Router execute(HttpServletRequest request) {
        User.UserRole currentRole=(User.UserRole) request.getSession().
                getAttribute(SessionAttribute.CURRENT_USER_ROLE.name());
        Router router;
        if(currentRole== User.UserRole.guest){
            request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.GUEST_CART_PAGE);
            router=new Router(PagePath.GUEST_CART_PAGE.getValue(), Router.RouterType.FORWARD);
        }else{
            request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.USER_CART_PAGE);
            router=new Router(PagePath.USER_CART_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return router;
    }
}
