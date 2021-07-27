package com.epam.webch.controller.command.impl;

import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.impl.RequestParameter;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.service.order.OrderService;
import com.epam.webch.model.service.order.impl.OrderServiceImpl;
import jakarta.faces.convert.DateTimeConverter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CreateOrder implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();
    private Order.OrderStatus DEFAULT_ORDER_STATUS = Order.OrderStatus.processed;

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        long last;
        String details=request.getParameter(RequestParameter.DETAILS.name());
        String stringDate=request.getParameter(RequestParameter.DATE.name());
        List<Product> productList = (List<Product>) request.getSession().getAttribute(SessionAttribute.USER_CART.name());
        try {
            Optional<Long> lastId = orderService.findLastOrderId();
            if(!lastId.isPresent()){
                last=0;
                for(Product product:productList){
                    orderService.addOrder(product.getId(),DEFAULT_ORDER_STATUS,last,)
                }
            }
        } catch (ServiceException e) {
           //todo error
        }
        return null;
    }
}
