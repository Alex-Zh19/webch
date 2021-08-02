package com.epam.webch.controller.command.impl;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.CommandName;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.impl.RequestParameter;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.service.product.ProductService;
import com.epam.webch.model.service.product.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class AddToCart implements Command {
    private ProductService productService = ProductServiceImpl.getInstance();
    private static final Logger logger= LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router result;
        String productId = request.getParameter(RequestParameter.PRODUCT_ID.getValue());
        Optional<Product> productOptional = Optional.empty();
        PagePath prevPage=(PagePath) request.getSession().getAttribute(SessionAttribute.PREVIOUS_PAGE.name());
        try {
            productOptional = productService.findProductById(Long.parseLong(productId));
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"service exception at AddToCart");
            result = new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        if (productOptional.isPresent()) {
            List<Product> productList = (List<Product>) request.getSession().getAttribute(SessionAttribute.USER_CART.name());
            productList.add(productOptional.get());
            result = new Router(prevPage.getValue(), Router.RouterType.FORWARD);
        }else{
            logger.log(Level.ERROR,"service exception at AddToCart");
            result=new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return result;
    }
}
