package com.epam.webch.controller.command.impl.employee;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.impl.RequestParameter;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.service.order.OrderService;
import com.epam.webch.model.service.order.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeOrderInfo implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String stringId = (String) request.getParameter(RequestParameter.ORDER_NUMBER.getValue());
        Long orderNumber = Long.parseLong(stringId);
        User currentUser = (User) request.getSession().getAttribute(SessionAttribute.CURRENT_USER.name());
        String statusToChange = request.getParameter(RequestParameter.ORDER_STATUS_TO_CHANGE.getValue());
        Order.OrderStatus status = Order.OrderStatus.valueOf(statusToChange);
        Router router = switch (status) {
            case preparing ->preparingCase(currentUser,orderNumber);
            case ready -> readyCase(orderNumber);
            case deleted -> deleteCase(orderNumber);
            default -> new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
        };
        return router;
    }

    private Router preparingCase(User currentUser, Long orderNumber) {
        try {
            orderService.changeOrderRecipient(orderNumber, currentUser.getId());
            orderService.changeOrderStatus(orderNumber, Order.OrderStatus.preparing);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Service exception at preparingCase Change Order Status {}", e);
            return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ADMIN_EMPLOYEE_ORDER_LIST_PAGE.name(), Router.RouterType.FORWARD);
    }
    private Router readyCase( Long orderNumber) {
        try {
            orderService.changeOrderStatus(orderNumber, Order.OrderStatus.ready);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Service exception at readyCase Change Order Status {}", e);
            return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ADMIN_EMPLOYEE_ORDER_LIST_PAGE.name(), Router.RouterType.FORWARD);
    }
    private Router deleteCase( Long orderNumber) {
        try {
            orderService.changeOrderStatus(orderNumber, Order.OrderStatus.deleted);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Service exception at deleteCase Change Order Status {}", e);
            return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ADMIN_EMPLOYEE_ORDER_LIST_PAGE.name(), Router.RouterType.FORWARD);
    }
}
