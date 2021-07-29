package com.epam.webch.controller.command.impl.admin;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OpenProductsList implements Command {
    private ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.ADMIN_PRODUCT_LIST_PAGE);
        List<Product> productList = new ArrayList<>();
        try {
            List<Optional<Product>> list = productService.findAllProducts();
            for (Optional<Product> product : list) {
                if (product.isPresent()) {
                    productList.add(product.get());
                }
            }
            request.setAttribute(RequestParameter.PRODUCT_LIST.name(), productList);
        } catch (ServiceException e) {
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ADMIN_PRODUCT_LIST_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}

