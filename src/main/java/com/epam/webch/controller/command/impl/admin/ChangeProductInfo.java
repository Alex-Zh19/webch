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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ChangeProductInfo implements Command {
    private static final Logger logger = LogManager.getLogger();
    private ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        PagePath prevPage = (PagePath) request.getSession().getAttribute(SessionAttribute.PREVIOUS_PAGE.name());
        Optional<String> name = Optional.ofNullable(request.getParameter(RequestParameter.PRODUCT_NAME.getValue()));
        Optional<String> price = Optional.ofNullable(request.getParameter(RequestParameter.PRODUCT_PRICE.getValue()));
        Optional<String> description = Optional.ofNullable(request.getParameter(RequestParameter.PRODUCT_DESCRIPTION.getValue()));
        Optional<String> inStock = Optional.ofNullable(request.getParameter(RequestParameter.PRODUCT_IN_STOCK.getValue()));
        Optional<Product> optionalProduct;
        String stringProdId = request.getParameter(RequestParameter.PRODUCT_ID.getValue());
        Long prodId = Long.parseLong(stringProdId);
        try {
            optionalProduct = productService.findProductById(prodId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Service exception at ChangeProductInfo info {}", e);
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        if (optionalProduct.isPresent()) {
            Product product=optionalProduct.get();
            if ( !name.isEmpty()&&!product.getName().equals(name.get()) ) {
                try {
                    productService.changeProductName(prodId, name.get());
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, "Service exception at ChangeProductInfo info {}", e);
                    return new Router(prevPage.getValue(), Router.RouterType.FORWARD);
                }
            }
            if (!price.isEmpty() &&!(product.getPrice()==Integer.parseInt(price.get())) ) {
                try {
                    productService.changeProductPrice(prodId,Integer.parseInt(price.get()));
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, "Service exception at ChangeProductInfo info {}", e);
                    return new Router(prevPage.getValue(), Router.RouterType.FORWARD);
                }
            }
            if (!description.isEmpty()&&!product.getDescription().equals(description.get())  ) {
                try {
                    productService.changeProductDescription(prodId, description.get());
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, "Service exception at ChangeProductInfo info {}", e);
                    return new Router(prevPage.getValue(), Router.RouterType.FORWARD);
                }
            }
            if (!inStock.isEmpty()&&!(product.getIsInStock()==Integer.parseInt(inStock.get()))) {
                try {
                    productService.changeProductInStock(prodId, Integer.parseInt(inStock.get()));
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, "Service exception at ChangeProductInfo info {}", e);
                    return new Router(prevPage.getValue(), Router.RouterType.FORWARD);
                }
            }

        }
        return new Router(PagePath.HOME_USER_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}

