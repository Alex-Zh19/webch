package com.epam.webch.controller.command.impl.all;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.webch.model.entity.user.User.UserRole.*;
import static com.epam.webch.model.entity.user.User.UserRole.admin;

public class OpenShopPage implements Command {
    private ProductService productService = ProductServiceImpl.getInstance();

    @AllowedRole({guest,user,employee,admin})
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.SHOP_PAGE);
        List<Product> productList=new ArrayList<>();
        try {
            List<Optional<Product>> list = productService.findAllProducts();
            for(Optional<Product> product:list){
                if(product.isPresent()&&product.get().IsInStock()!=0){
                    productList.add(product.get());
                }
            }
            request.getSession().setAttribute(SessionAttribute.CURRENT_ENTITY_TO_DISPLAY.name(), productList);
        } catch (ServiceException e) {
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.SHOP_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
