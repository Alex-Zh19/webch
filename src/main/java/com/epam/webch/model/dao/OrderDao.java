package com.epam.webch.model.dao;

import com.epam.webch.exception.DaoException;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderDao{

    List<Optional<Order>> findAllOrders() throws DaoException;
    List<Optional<Order>> findOrdersByCreator(User creator) throws DaoException;
    Optional<Order> findOrder(Long idInBase) throws DaoException;
    void addOrder(long productId, Order.OrderStatus status, long detailsId, String details, Date date, User creator, User recipient) throws DaoException;
    void addOrder(long productId, Order.OrderStatus status,  long detailsId, String details, Date date,  User creator) throws DaoException;
    void deleteOrder(Order order) throws DaoException;
    void reallyDeleteOrder(Order order) throws DaoException;
    void changeOrderStatus(Long id, Order.OrderStatus status)
            throws DaoException;

    Optional<Long>findLastOrderId() throws DaoException;
}
