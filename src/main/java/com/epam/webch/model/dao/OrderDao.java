package com.epam.webch.model.dao;

import com.epam.webch.exception.DaoException;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OrderDao{

    List<Optional<Order>> findAllOrders() throws DaoException;
    List<Optional<Order>> findOrdersByCreator(User creator) throws DaoException;
    List< Optional<Order>> findOrder(Long order_id) throws DaoException;
    boolean addOrderDetails(long detailsId, String details, Date date) throws DaoException;
    Optional<Long> findOrderDetails(long detailsId) throws DaoException;
    void addOrder(long orderId,long productId, Order.OrderStatus status,  long detailsId,  User creator) throws DaoException;
    void addOrder(long orderId,long productId, Order.OrderStatus status,  long detailsId) throws DaoException;
    void deleteOrder(Order order) throws DaoException;
    void reallyDeleteOrder(Order order) throws DaoException;
    void changeOrderStatus(Long id, Order.OrderStatus status)
            throws DaoException;
    void changeOrderRecipient(Long id, Long userId)
            throws DaoException;

    Optional<Long>findLastOrderId() throws DaoException;
}
