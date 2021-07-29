package com.epam.webch.model.service.user;

import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends UserService{
    Optional<User> findUserByEmail(String emailToFind) throws ServiceException;
    Optional<User> findUserById(Long id) throws ServiceException;


    //find all orders, change order status, find all products



}
