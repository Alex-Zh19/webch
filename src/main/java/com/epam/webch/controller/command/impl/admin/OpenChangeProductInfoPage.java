package com.epam.webch.controller.command.impl.admin;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
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

import static com.epam.webch.controller.impl.RequestParameter.PRODUCT;
import static com.epam.webch.controller.impl.RequestParameter.PRODUCT_ID;

public class OpenChangeProductInfoPage implements Command {
    private ProductService productService= ProductServiceImpl.getInstance();
    private static final Logger logger= LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String stringId=request.getParameter(PRODUCT_ID.getValue());
        Long prodId=Long.parseLong(stringId);
        Optional<Product> product;
        try{
            product=productService.findProductById(prodId);
            if(product.isPresent()){
                request.setAttribute(PRODUCT.name(),product.get());
            }
        }catch (ServiceException e){
            logger.log(Level.ERROR,"service exception at OpenChangeProductInfoPage");
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ADMIN_PRODUCT_SETTINGS_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
