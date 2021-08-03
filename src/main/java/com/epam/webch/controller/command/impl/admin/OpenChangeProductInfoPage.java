package com.epam.webch.controller.command.impl.admin;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.service.product.ProductService;
import com.epam.webch.model.service.product.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Optional;

import static com.epam.webch.controller.command.impl.impl.RequestParameter.PRODUCT_ID;
import static com.epam.webch.model.entity.user.User.UserRole.admin;

public class OpenChangeProductInfoPage implements Command {
    private ProductService productService= ProductServiceImpl.getInstance();
    private static final Logger logger= LogManager.getLogger();
    @AllowedRole({admin})
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.ADMIN_PRODUCT_SETTINGS_PAGE);
        String stringId=request.getParameter(PRODUCT_ID.getValue());
        Long prodId=Long.parseLong(stringId);
        Optional<Product> product;
        try{
            product=productService.findProductById(prodId);
            if(product.isPresent()){
                request.getSession().setAttribute(SessionAttribute.CURRENT_ENTITY_TO_DISPLAY.name(), product.get());
            }
        }catch (ServiceException e){
            logger.log(Level.ERROR,"service exception at OpenChangeProductInfoPage");
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ADMIN_PRODUCT_SETTINGS_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
