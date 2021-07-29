package com.epam.webch.controller.command.impl.admin;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.impl.RequestParameter;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.service.order.OrderService;
import com.epam.webch.model.service.order.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OpenOrdersList implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.ADMIN_ORDER_LIST_PAGE);
        List<Order> orderList = new ArrayList<>();
        try {
            List<Optional<Order>> list = orderService.findAllOrders();
            for (Optional<Order> order : list) {
                if (order.isPresent()) {
                    orderList.add(order.get());
                }
            }
            request.setAttribute(RequestParameter.ORDER_LIST.name(), orderList);
        } catch (ServiceException e) {
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ADMIN_ORDER_LIST_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
