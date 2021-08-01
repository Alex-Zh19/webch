package com.epam.webch.controller.command.impl;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.model.entity.product.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class OpenOrderingPage implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
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
