package com.epam.webch.controller.command.impl.all;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.model.entity.product.Product;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.epam.webch.model.entity.user.User.UserRole.*;
import static com.epam.webch.model.entity.user.User.UserRole.admin;

public class OpenOrderingPage implements Command {

    @AllowedRole
    @Override
    public Router execute(HttpServletRequest request) {
        Router result;
        List<Product> productList = (List<Product>) request.getSession().getAttribute(SessionAttribute.USER_CART.name());
        if (productList.isEmpty()) {
            result = new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
        } else {
            request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.ORDERING_PAGE);
            result = new Router(PagePath.ORDERING_PAGE.getValue(), Router.RouterType.FORWARD);
        }

        return result;
    }
}
