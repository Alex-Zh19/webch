package com.epam.webch.controller.command.impl;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.impl.RequestParameter;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.service.product.ProductService;
import com.epam.webch.model.service.product.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class OpenProductPage implements Command {
    private ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.PRODUCT_PAGE);
        String stringId = request.getParameter(RequestParameter.PRODUCT_ID.getValue());
        Optional<Product> product;
        Long id = Long.parseLong(stringId);
        try {
            product = productService.findProductById(id);
        }catch (ServiceException e){
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        if(product.isPresent()){
            request.getSession().setAttribute(SessionAttribute.CURRENT_ENTITY_TO_DISPLAY.name(),product.get());
            return new Router(PagePath.PRODUCT_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
