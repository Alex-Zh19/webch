package com.epam.webch.controller.command.impl;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.impl.RequestParameter;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.service.order.OrderService;
import com.epam.webch.model.service.order.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


public class CreateOrder implements Command {
    private static final Logger logger= LogManager.getLogger();
    private OrderService orderService = OrderServiceImpl.getInstance();
    private Order.OrderStatus DEFAULT_ORDER_STATUS = Order.OrderStatus.processed;
    private Date DEFAULT_DATE = new Date(19 / 07 / 2000);
    private final String NAME_LABEL = "Name: ";
    private final String SURNAME_LABEL = "Surname: ";
    private final String ADDRESS_LABEL = "Address: ";

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        long last;
        String name = request.getParameter(RequestParameter.NAME_OF_ORDER_CREATOR.getValue());
        String surname = request.getParameter(RequestParameter.SURNAME_OF_ORDER_CREATOR.getValue());
        String address = request.getParameter(RequestParameter.ADDRESS_ORDER_CREATOR.getValue());
        StringBuilder details = new StringBuilder(NAME_LABEL);
        details.append(name).append(", ").append(SURNAME_LABEL).append(surname).append(", ").
                append(ADDRESS_LABEL).append(address);
        String stringDate = request.getParameter(RequestParameter.DATE.getValue());
        User.UserRole currentRole =
                (User.UserRole) request.getSession().getAttribute(SessionAttribute.CURRENT_USER_ROLE.name());
        Date date=Date.valueOf(stringDate);
        if (currentRole== User.UserRole.guest) {
            List<Product> productList = (List<Product>) request.getSession().getAttribute(SessionAttribute.USER_CART.name());
            try {
                Optional<Long> lastId = orderService.findLastOrderId();
                if (!lastId.isPresent()) {
                    last=0;
                    orderService.addOrderDetails( last, details.toString(), date);
                    for (Product product : productList) {
                        orderService.addOrder(last,product.getId(), DEFAULT_ORDER_STATUS, last);
                    }
                } else {
                    last = lastId.get() + 1;
                    orderService.addOrderDetails( last, details.toString(), date);
                    for (Product product : productList) {
                        orderService.addOrder(last,product.getId(), DEFAULT_ORDER_STATUS, last);
                    }
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR,"service exception at CreateOrder");
                return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
            }
            return new Router(PagePath.SUCCESSFUL_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);

        } else {
            Optional<User> currentUser = Optional.ofNullable(
                    (User) request.getSession().getAttribute(SessionAttribute.CURRENT_USER.name()));
            List<Product> productList = (List<Product>) request.getSession().getAttribute(SessionAttribute.USER_CART.name());
            try {
                Optional<Long> lastId = orderService.findLastOrderId();
                if (!lastId.isPresent()) {
                    last = 0;
                    orderService.addOrderDetails( last, details.toString(), date);
                    for (Product product : productList) {
                        orderService.addOrder(last,product.getId(), DEFAULT_ORDER_STATUS, last,currentUser.get());
                    }
                } else {
                    last = lastId.get() + 1;
                    orderService.addOrderDetails( last, details.toString(), date);
                    for (Product product : productList) {
                        orderService.addOrder(last,product.getId(), DEFAULT_ORDER_STATUS, last, currentUser.get());
                    }
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR,"service exception at CreateOrder");
                return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
            }
            return new Router(PagePath.SUCCESSFUL_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
        }
    }
}
