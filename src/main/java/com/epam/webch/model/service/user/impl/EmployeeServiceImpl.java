package com.epam.webch.model.service.user.impl;

import com.epam.webch.exception.DaoException;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.dao.OrderDao;
import com.epam.webch.model.dao.ProductDao;
import com.epam.webch.model.dao.UserDao;
import com.epam.webch.model.dao.impl.OrderDaoImpl;
import com.epam.webch.model.dao.impl.ProductDaoImpl;
import com.epam.webch.model.dao.impl.UserDaoImpl;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.service.user.AdminService;
import com.epam.webch.model.service.user.EmployeeService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl extends UserServiceImpl implements EmployeeService {
    private static EmployeeService instance;
    private static final Logger logger = LogManager.getLogger();
    private static final UserDao userDao = UserDaoImpl.getInstance();
    private static final OrderDao orderDao = OrderDaoImpl.getInstance();
    private static final ProductDao productDao = ProductDaoImpl.getInstance();


    EmployeeServiceImpl() {
    }

    public static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> findUserByEmail(String emailToFind) throws ServiceException {
        Optional<User> user = Optional.empty();
        if (emailToFind == null) {
            return user;
        }
        try {
            user = userDao.findUserByEmail(emailToFind);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at findUserByEmail method. {}", e);
            throw new ServiceException("DaoException at findUserByEmail method. " + e);
        }
        return user;
    }

}
