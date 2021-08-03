package com.epam.webch.controller.command.impl.admin;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.command.impl.impl.RequestParameter;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.service.product.ProductService;
import com.epam.webch.model.service.product.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.epam.webch.model.entity.user.User.UserRole.admin;

public class ChangeProductInfo implements Command {
    private static final Logger logger = LogManager.getLogger();
    private ProductService productService = ProductServiceImpl.getInstance();
    @AllowedRole({admin})
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.ADMIN_PRODUCT_SETTINGS_PAGE);
        Optional<String> name = Optional.ofNullable(request.getParameter(RequestParameter.PRODUCT_NAME.getValue()));
        Optional<String> price = Optional.ofNullable(request.getParameter(RequestParameter.PRODUCT_PRICE.getValue()));
        Optional<String> description = Optional.ofNullable(request.getParameter(RequestParameter.PRODUCT_DESCRIPTION.getValue()));
        Optional<String> stringInStock = Optional.ofNullable(request.getParameter(RequestParameter.PRODUCT_IN_STOCK.getValue()));
        Integer inStock=0;
        if(stringInStock.isPresent()){
            inStock=Integer.parseInt(stringInStock.get());
        }
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
            request.getSession().setAttribute(SessionAttribute.CURRENT_ENTITY_TO_DISPLAY.name(),product );
            if ( !name.isEmpty()&&!product.getName().equals(name.get()) ) {
                try {
                    productService.changeProductName(prodId, name.get());
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, "Service exception at ChangeProductInfo info {}", e);
                    return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
                }
            }
            if (!price.isEmpty() &&!(product.getPrice()==Integer.parseInt(price.get())) ) {
                try {
                    productService.changeProductPrice(prodId,Integer.parseInt(price.get()));
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, "Service exception at ChangeProductInfo info {}", e);
                    return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
                }
            }
            if (!description.isEmpty()&&!product.getDescription().equals(description.get())  ) {
                try {
                    productService.changeProductDescription(prodId, description.get());
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, "Service exception at ChangeProductInfo info {}", e);
                    return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
                }
            }
            if (!(product.getIsInStock()==inStock)) {
                try {
                    productService.changeProductInStock(prodId, inStock);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, "Service exception at ChangeProductInfo info {}", e);
                    return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
                }
            }

        }

        return new Router(PagePath.ADMIN_PRODUCT_SETTINGS_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}

