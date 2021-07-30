package com.epam.webch.model.service.order;

import com.epam.webch.exception.DaoException;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Optional<Order>> findAllOrders() throws ServiceException;
    List<Optional<Order>> findOrdersByCreator(User creator) throws ServiceException;
    List<Optional<Order>> findOrder(Long idInBase) throws ServiceException;
    boolean addOrderDetails( long detailsId, String details, Date date) throws ServiceException;
    Optional<Long> findOrderDetails(long detailsId) throws ServiceException;
    boolean addOrder(long orderId,long productId, Order.OrderStatus status, long detailsId, User creator) throws ServiceException;
    boolean addOrder(long orderId,long productId, Order.OrderStatus status, long detailsId) throws ServiceException;
    boolean deleteOrder(Order order) throws ServiceException;
    boolean changeOrderStatus(Long id, Order.OrderStatus status)
            throws ServiceException;
    boolean changeOrderRecipient(Long id, Long userId)
            throws ServiceException;

    Optional<Long>findLastOrderId() throws ServiceException;
    boolean reallyDeleteOrder(Order order) throws ServiceException;
}
