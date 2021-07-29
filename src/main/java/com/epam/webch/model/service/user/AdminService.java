package com.epam.webch.model.service.user;

import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface AdminService extends EmployeeService{
    List<Optional<User>> findAllUsers() throws ServiceException;
    List<Optional<User>> findAllEmployees() throws ServiceException;
    boolean changeUserStatus(String email, User.UserStatus status) throws ServiceException;
    boolean changeUserRole(String email, User.UserRole role)throws ServiceException;

    //add product, findProductById, delete product, changeProduct price, change product name

}
