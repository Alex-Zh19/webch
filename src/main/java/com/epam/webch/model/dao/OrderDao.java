package com.epam.webch.model.dao;

import com.epam.webch.exception.DaoException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface OrderDao{

    List<Optional<Order>> findAllOrders() throws DaoException;
    List<Optional<Order>> findOrdersByCreator(User creator) throws DaoException;
    Optional<Order> findOrder(Long idInBase) throws DaoException;
    void addOrder(Product product, Order.OrderStatus status, String details, User creator, User recipient) throws DaoException;
    void addOrder(Product product, Order.OrderStatus status, User creator, User recipient) throws DaoException;
    void addOrder(Product product, Order.OrderStatus status, String details, User creator) throws DaoException;
    void addOrder(Product product, Order.OrderStatus status, User creator) throws DaoException;
    void deleteOrder(Order order) throws DaoException;
    void reallyDeleteOrder(Order order) throws DaoException;
    void changeOrderStatus(Long id, Order.OrderStatus status)
            throws DaoException;


}
