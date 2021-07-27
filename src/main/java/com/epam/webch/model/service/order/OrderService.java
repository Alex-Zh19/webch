package com.epam.webch.model.service.order;

import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Optional<Order>> findAllOrders() throws ServiceException;
    List<Optional<Order>> findOrdersByCreator(User creator) throws ServiceException;
    Optional<Order> findOrder(Long idInBase) throws ServiceException;
    boolean addOrder(long productId, Order.OrderStatus status, long detailsId, String details, Date date,
                     User creator, Optional<User> recipient) throws ServiceException;
    boolean deleteOrder(Order order) throws ServiceException;
    boolean changeOrderStatus(Long id, Order.OrderStatus status)
            throws ServiceException;

    Optional<Long>findLastOrderId() throws ServiceException;
    boolean reallyDeleteOrder(Order order) throws ServiceException;
}
