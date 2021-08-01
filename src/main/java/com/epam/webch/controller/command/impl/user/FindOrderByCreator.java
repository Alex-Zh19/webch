package com.epam.webch.controller.command.impl.user;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindOrderByCreator implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();
    private static final Logger logger= LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.USER_ORDER_LIST_PAGE);
        User currentUser=(User)request.getSession().getAttribute(SessionAttribute.CURRENT_USER.name());
        List<Order> orderList = new ArrayList<>();
        try {
            List<Optional<Order>> list = orderService.findOrdersByCreator(currentUser);
            for (Optional<Order> order : list) {
                if (order.isPresent()) {
                    orderList.add(order.get());
                }
            }
            request.setAttribute(RequestParameter.ORDER_LIST.name(), orderList);
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"service exception at FindOrderByCreator");
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.USER_ORDER_LIST_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
