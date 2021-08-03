package com.epam.webch.controller.command.impl.employee;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.service.order.OrderService;
import com.epam.webch.model.service.order.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.webch.model.entity.user.User.UserRole.*;

public class OpenOrdersList implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();
    private static final Logger logger= LogManager.getLogger();
    @AllowedRole({employee,admin})
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.ADMIN_EMPLOYEE_ORDER_LIST_PAGE);
        List<Order> orderList = new ArrayList<>();
        try {
            List<Optional<Order>> list = orderService.findAllOrders();
            for (Optional<Order> order : list) {
                if (order.isPresent()) {
                    orderList.add(order.get());
                }
            }
            request.getSession().setAttribute(SessionAttribute.CURRENT_ENTITY_TO_DISPLAY.name(), orderList);
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"service exception at OpenOrdersList");
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ADMIN_EMPLOYEE_ORDER_LIST_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
