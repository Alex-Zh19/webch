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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class AdminServiceImpl extends EmployeeServiceImpl implements AdminService {
    private static AdminService instance;
    private static final Logger logger = LogManager.getLogger();
    private static final UserDao userDao = UserDaoImpl.getInstance();

    private AdminServiceImpl() {
    }

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Optional<User>> findAllUsers() throws ServiceException {
        List<Optional<User>> users;
        try {
            users = userDao.findAllUsers();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at findAllUsers method. {}", e);
            throw new ServiceException("DaoException at findAllUsers method. " + e);
        }
        return users;
    }

    @Override
    public List<Optional<User>> findAllEmployees() throws ServiceException {
        List<Optional<User>> employee;
        try {
            employee = userDao.findAllEmployees();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at findAllEmployees method. {}", e);
            throw new ServiceException("DaoException at findAllEmployees method. " + e);
        }
        return employee;
    }


    @Override
    public boolean changeUserStatus(String email, User.UserStatus status) throws ServiceException {

        try {
            userDao.changeUserStatus(email, status);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at changeUserStatus method. {}", e);
            throw new ServiceException("DaoException at changeUserStatus method. " + e);
        }

    }

    @Override
    public boolean changeUserRole(String email, User.UserRole role) throws ServiceException {
        try {
            userDao.changeUserRole(email, role);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at changeUserRole method. {}", e);
            throw new ServiceException("DaoException at changeUserRole method. " + e);
        }
    }

}
