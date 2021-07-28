package com.epam.webch.model.service.order.impl;

import com.epam.webch.exception.DaoException;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.dao.OrderDao;
import com.epam.webch.model.dao.impl.OrderDaoImpl;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.service.order.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger();
    private static OrderService instance;
    private OrderDao orderDao = OrderDaoImpl.getInstance();

    public static OrderService getInstance() {
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
    public Optional<Long> findLastOrderId() throws ServiceException {
        Optional<Long> id;
        try {
            id = orderDao.findLastOrderId();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at findLastOrderId method. {}", e);
            throw new ServiceException("DaoException at findLastOrderId method. " + e);
        }
        return id;
    }

    @Override
    public Optional<Long> findOrderDetails(long detailsId) throws ServiceException {
        Optional<Long> id = Optional.empty();
        try {
            id = orderDao.findOrderDetails(detailsId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at findOrderDetails method. {}", e);
            throw new ServiceException("DaoException at findOrderDetails method. " + e);
        }
        return id;
    }

    @Override
    public boolean addOrderDetails(long detailsId, String details, Date date) throws ServiceException {
        try {
            orderDao.addOrderDetails(detailsId, details, date);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at addOrderDetails method. {}", e);
            throw new ServiceException("DaoException at addOrderDetails method. " + e);
        }
    }

    @Override
    public boolean addOrder(long orderId,long productId, Order.OrderStatus status, long detailsId) throws ServiceException {
        try {
            orderDao.addOrder(orderId,productId, status, detailsId);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at addOrder method. {}", e);
            throw new ServiceException("DaoException at addOrder method. " + e);
        }
    }

    @Override
    public boolean addOrder(long orderId,long productId, Order.OrderStatus status, long detailsId, User creator) throws ServiceException {

        if (creator == null) {
            return false;
            //todo redirect on error page
        }
        try {
            orderDao.addOrder(orderId,productId, status, detailsId, creator);
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
