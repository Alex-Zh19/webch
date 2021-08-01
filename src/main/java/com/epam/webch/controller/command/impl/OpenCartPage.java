package com.epam.webch.controller.command.impl;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class OpenCartPage implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        User.UserRole currentRole=(User.UserRole) request.getSession().
                getAttribute(SessionAttribute.CURRENT_USER_ROLE.name());
        Router router;
        if(currentRole== User.UserRole.guest){
            request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.GUEST_CART_PAGE);
            List<Product> productList=(List<Product>)
                    request.getSession().getAttribute(SessionAttribute.USER_CART.name());
            System.out.println(productList);
            router=new Router(PagePath.GUEST_CART_PAGE.getValue(), Router.RouterType.FORWARD);
        }else{
            request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.USER_CART_PAGE);
            router=new Router(PagePath.USER_CART_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return router;
    }
}
