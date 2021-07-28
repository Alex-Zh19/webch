package com.epam.webch.model.dao;

import com.epam.webch.exception.DaoException;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.entity.user.UserCredentials;

import java.util.List;
import java.util.Optional;

public interface UserDao{

    Optional<User> findUserByEmail(String emailToFind) throws DaoException;
    Optional<User> findUserById(Long id) throws DaoException;
    List<Optional<User>> findAllUsers() throws DaoException;
    Optional<UserCredentials> findUserCredentialsByEmail(String emailToFind) throws DaoException;
    boolean deleteUser(Long id) throws DaoException;
    void changeUserStatus(String email, User.UserStatus status) throws DaoException;
    void changeUserRole(String email, User.UserRole role)throws DaoException;
    void changeUserName(String email, String name) throws DaoException;
    void changeUserSurname(String email, String surname)throws DaoException;
    void changeUserEmail(String email, String newEmail) throws DaoException;
    void changeUserPassword(String email, String password,String salt)throws DaoException;
    void createUser(String email, String hashPassword, String salt, String name,
                    String surname, int balance, User.UserRole role, User.UserStatus status) throws DaoException;
    void createUser(String email,String hashPassHex,String saltHex,String name,int balance,
                    User.UserRole role,User.UserStatus status) throws DaoException;
    void createUser(String email, String hashPassHex, String saltHex,
                           String name) throws DaoException;
    void createUser(String email, String hashPassHex, String saltHex,
                    String name,String surname) throws DaoException;

}
