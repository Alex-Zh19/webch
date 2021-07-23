package com.epam.webch.model.service.order.impl;

import com.epam.webch.exception.DaoException;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.dao.OrderDao;
import com.epam.webch.model.dao.impl.OrderDaoImpl;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.service.order.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger();
    private OrderService instance;
    private OrderDao orderDao = OrderDaoImpl.getInstance();

    public OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Optional<Order>> findAllOrders() throws ServiceException {
        List<Optional<Order>> orders;
        try {
            orders = orderDao.findAllOrders();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at findAllOrders method. {}", e);
            throw new ServiceException("DaoException at findAllOrders method. " + e);
        }
        return orders;
    }

    @Override
    public List<Optional<Order>> findOrdersByCreator(User creator) throws ServiceException {
        if (creator == null) {
            throw new ServiceException("DaoException at getOrdersByCreator method. creator cannot be null ");
        }
        List<Optional<Order>> orders;
        try {
            orders = orderDao.findOrdersByCreator(creator);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at getOrdersByCreator method. {}", e);
            throw new ServiceException("DaoException at getOrdersByCreator method. " + e);
        }
        return orders;
    }

    @Override
    public Optional<Order> findOrder(Long idInBase) throws ServiceException {
        Optional<Order> order;
        try {
            order = orderDao.findOrder(idInBase);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at getOrderById method. {}", e);
            throw new ServiceException("DaoException at getOrderById method. " + e);
        }
        return order;
    }

    @Override
    public boolean addOrder(Product product, Order.OrderStatus status, Optional<String> details,
                            User creator, Optional<User> recipient) throws ServiceException {

        if (product == null) {
            return false;
            //todo redirect on error page
        }
        if (creator == null) {
            return false;
            //todo redirect on error page
        }
        try {
            if (details.isPresent() && recipient.isPresent()) {
                orderDao.addOrder(product, status, details.get(), creator, recipient.get());
            } else if (details.isPresent()) {
                orderDao.addOrder(product, status, details.get(), creator);
            } else if (recipient.isPresent()) {
                orderDao.addOrder(product, status, creator, recipient.get());
            } else {
                orderDao.addOrder(product, status, creator);
            }
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at addOrder method. {}", e);
            throw new ServiceException("DaoException at addOrder method. " + e);
        }
    }

    @Override
    public boolean deleteOrder(Order order) throws ServiceException {
        if (order == null) {
            return false;
            //todo redirect on error page
        }
        try {
            orderDao.deleteOrder(order);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at deleteOrder method. {}", e);
            throw new ServiceException("DaoException at deleteOrder method. " + e);
        }
    }

    @Override
    public boolean changeOrderStatus(Long id, Order.OrderStatus status) throws ServiceException {
        try {
            orderDao.changeOrderStatus(id, status);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at changeOrderStatus method. {}", e);
            throw new ServiceException("DaoException at changeOrderStatus method. " + e);
        }
    }


    @Override
    public boolean reallyDeleteOrder(Order order) throws ServiceException {

        try {
            orderDao.reallyDeleteOrder(order);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at reallyDeleteOrder method. {}", e);
            throw new ServiceException("DaoException at reallyDeleteOrder method. " + e);
        }

    }

}
