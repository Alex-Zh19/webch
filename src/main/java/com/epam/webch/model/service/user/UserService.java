package com.epam.webch.model.service.user;

import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //UserDao
    Optional<User> signInUser(String email, String password) throws ServiceException;
    boolean signUpUser(String email, String password, String confirmPassword, String name,
                       Optional<String> surname, int balance,
                       User.UserRole role, User.UserStatus status) throws ServiceException;

    boolean signUpUser(String email, String password, String confirmPassword, String name,
                       Optional<String> surname) throws ServiceException;
    boolean changeUserName(String email, String name) throws ServiceException;
    boolean changeUserSurname(String email, String surname)throws ServiceException;
    boolean changeUserEmail(String email,String newEmail) throws ServiceException;
    boolean changeUserPassword(String email,String oldPass, String newPassword,
                               String confirmPassword)throws ServiceException;
    boolean signOutUser(Long id)throws ServiceException;

    //find ordersByCreator, find order, add order, delete order



}
