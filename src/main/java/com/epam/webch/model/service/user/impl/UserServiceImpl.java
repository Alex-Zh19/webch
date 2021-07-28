package com.epam.webch.model.service.user.impl;

import com.epam.webch.exception.DaoException;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.exception.UtilException;
import com.epam.webch.model.dao.OrderDao;
import com.epam.webch.model.dao.UserDao;
import com.epam.webch.model.dao.impl.OrderDaoImpl;
import com.epam.webch.model.dao.impl.UserDaoImpl;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.entity.user.UserCredentials;
import com.epam.webch.model.service.user.UserService;
import com.epam.webch.model.util.Encryptor;
import com.epam.webch.model.util.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Base64;
import java.util.Optional;

public class UserServiceImpl implements UserService {
   private static UserService instance;
   private static final Logger logger = LogManager.getLogger();
   private UserDao userDao = UserDaoImpl.getInstance();
   private OrderDao orderDao = OrderDaoImpl.getInstance();


   UserServiceImpl() {
   }

   public static UserService getInstance() {
      if (instance == null) {
         instance = new UserServiceImpl();
      }
      return instance;
   }


   @Override
   public Optional<User> signInUser(String email, String password) throws ServiceException {
      Optional<User> user = Optional.empty();
      try {
         Optional<UserCredentials> userCredentials = userDao.findUserCredentialsByEmail(email);
         if (userCredentials.isPresent()) {
            String saltHex = userCredentials.get().getSaltHex();
            byte[] salt = Base64.getDecoder().decode(saltHex);
            String enteredPasswordHex;
            enteredPasswordHex = Encryptor.generateCredentials(password, salt);
            String passwordFromBaseHex = userCredentials.get().getHashPasswordHex();
            if (enteredPasswordHex.equals(passwordFromBaseHex)) {
               user = userDao.findUserByEmail(email);
            } else {
               //todo redirect on page to enter password one more time

            }
         } else {
            //todo redirect on page such user doesnt exist
         }
      } catch (DaoException e) {
         logger.log(Level.ERROR, "DaoException at sign in user method. {}", e);
         throw new ServiceException("DaoException at sign in user method. " + e);
      }
      return user;
   }


   @Override
   public boolean signUpUser(String email, String password, String confirmPassword, String name, Optional<String> surname) throws ServiceException {
      Optional<User> user;
      try {
         user = userDao.findUserByEmail(email);
      } catch (DaoException e) {
         logger.log(Level.ERROR, "UtilException at signUp user method. {}", e);
         throw new ServiceException("DaoException in signUp. " + e);
      }
      if (user.isPresent()) {
         return false;
      }
      if (!Validator.checkEmailOnCorrectness(email)) {
         return false;
         //todo redirect on page to enter email one more time
      }
      if (!password.equals(confirmPassword)) {
         return false;
         //todo redirect on page to enter confirmPassword one more time
      }
      if (!Validator.validatePassword(password)) {
         return false;
         //todo redirect on page to enter password one more time
      }
      if (!Validator.validateName(name)) {
         return false;
         //todo redirect on page to enter name one more time
      }
      if (surname.isPresent() && !Validator.validateSurname(surname.get())) {
         return false;
         //todo redirect on page to enter name one more time
      }
      UserCredentials userCredentials;
      userCredentials = Encryptor.generateCredentials(password);
      String hashPassHex = userCredentials.getHashPasswordHex();
      String saltHex = userCredentials.getSaltHex();
      if (hashPassHex != null && saltHex != null) {
         try {
            if (surname.isPresent()) {
               userDao.createUser(email, hashPassHex, saltHex, name, surname.get());
            } else {
               userDao.createUser(email, hashPassHex, saltHex, name);
            }
            return true;
         } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at register user method. {}", e);
            throw new ServiceException("DaoException at register user method. " + e);
         }
      } else {
         logger.log(Level.ERROR, "passwordHash and salt cannot be created at register user method. ");
         throw new ServiceException("passwordHash and salt cannot be created at register user method. ");
      }
   }

   @Override
   public boolean signUpUser(String email, String password, String confirmPassword, String name, Optional<String> surname,
                             int balance, User.UserRole role, User.UserStatus status) throws ServiceException {
      if (!Validator.checkEmailOnCorrectness(email)) {
         return false;
         //todo redirect on page to enter email one more time
      }
      if (!password.equals(confirmPassword)) {
         return false;
         //todo redirect on page to enter confirmPassword one more time
      }
      if (!Validator.validatePassword(password)) {
         return false;
         //todo redirect on page to enter password one more time
      }
      if (!Validator.validateName(name)) {
         return false;
         //todo redirect on page to enter name one more time
      }
      if (surname.isPresent() && !Validator.validateSurname(surname.get())) {
         return false;
         //todo redirect on page to enter name one more time
      }
      UserCredentials userCredentials;
      userCredentials = Encryptor.generateCredentials(password);
      String hashPassHex = userCredentials.getHashPasswordHex();
      String saltHex = userCredentials.getSaltHex();
      if (hashPassHex != null && saltHex != null) {
         try {
            if (surname.isPresent()) {
               userDao.createUser(email, hashPassHex, saltHex, name, surname.get(), balance, role, status);
            } else {
               userDao.createUser(email, hashPassHex, saltHex, name, balance, role, status);
            }
            return true;
         } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at register user method. {}", e);
            throw new ServiceException("DaoException at register user method. " + e);
         }
      } else {
         logger.log(Level.ERROR, "passwordHash and salt cannot be created at register user method. ");
         throw new ServiceException("passwordHash and salt cannot be created at register user method. ");
      }
   }

   @Override
   public boolean signOutUser(Long id) throws ServiceException {
      boolean result;
      try {
         Optional<User> user = userDao.findUserById(id);
         if (user.isPresent()) {
            userDao.deleteUser(id);
            result = true;
         } else {
            //todo redirect to error page
            result = false;
         }
      }catch (DaoException e){
         logger.log(Level.ERROR,"DaoException in deleteUser method. {}",e);
         throw new ServiceException("DaoException in deleteUser method. "+e);
      }
      return result;
   }

   @Override
   public boolean changeUserName(String email, String name) throws ServiceException {
      if (!Validator.validateName(name)) {
         return false;
      }
      boolean result;
      try {
         Optional<User> user = userDao.findUserByEmail(email);
         if (user.isPresent()) {
            userDao.changeUserName(email, name);
            result = true;
         } else {
            //todo redirect to error page
            result = false;
         }
      } catch (DaoException e) {
         logger.log(Level.ERROR, "DaoException in changeUserName method. {}", e);
         throw new ServiceException("DaoException in changeUserName method. " + e);
      }
      return result;
   }

   @Override
   public boolean changeUserSurname(String email, String surname) throws ServiceException {
      if (!Validator.validateSurname(surname)) {
         return false;
      }
      boolean result;
      try {
         Optional<User> user = userDao.findUserByEmail(email);
         if (user.isPresent()) {
            userDao.changeUserSurname(email, surname);
            result = true;
         } else {
            //todo redirect to error page
            result = false;
         }
      } catch (DaoException e) {
         logger.log(Level.ERROR, "DaoException in changeUserSurname method. {}", e);
         throw new ServiceException("DaoException in changeUserSurname method. " + e);
      }
      return result;
   }

   @Override
   public boolean changeUserEmail(String email, String newEmail) throws ServiceException {
      if (!Validator.checkEmailOnCorrectness(email)) {
         return false;
      }
      if (!Validator.checkEmailOnCorrectness(newEmail)) {
         //todo redirect on page to enter email again
         return false;
      }
      boolean result;
      try {
         Optional<User> user = userDao.findUserByEmail(email);
         if (user.isPresent()) {
            Optional<User> userWithNewEmail = userDao.findUserByEmail(newEmail);
            if (!userWithNewEmail.isPresent()) {
               userDao.changeUserEmail(email, newEmail);
            } else{
               //todo redirect on page to enter email again
               result = false;
            }
            result = true;
         } else {
            //todo redirect to error page
            result = false;
         }
      } catch (DaoException e) {
         logger.log(Level.ERROR, "DaoException in changeUserSurname method. {}", e);
         throw new ServiceException("DaoException in changeUserSurname method. " + e);
      }
      return result;
   }

   @Override
   public boolean changeUserPassword(String email, String oldPass, String newPassword,
                                     String confirmPassword) throws ServiceException {

      if (!newPassword.equals(confirmPassword)) {
         return false;
         //todo redirect on page to enter confirmPassword one more time
      }
      if (!Validator.validatePassword(newPassword)) {
         return false;
         //todo redirect on page to enter password one more time
      }
      if (!Validator.validatePassword(oldPass)) {
         return false;
         //todo redirect on page to enter password one more time
      }
      boolean result;
      try {
         Optional<UserCredentials> userCredentials = userDao.findUserCredentialsByEmail(email);
         if (userCredentials.isPresent()) {
            String saltHex = userCredentials.get().getSaltHex();
            byte[] salt = Base64.getDecoder().decode(saltHex);
            String enteredPasswordHex;
            enteredPasswordHex = Encryptor.generateCredentials(oldPass, salt);
            String passwordFromBaseHex = userCredentials.get().getHashPasswordHex();

            if (enteredPasswordHex.equals(passwordFromBaseHex)) {
               UserCredentials userCredentials1 = Encryptor.generateCredentials(newPassword);
               String hashPassHex = userCredentials1.getHashPasswordHex();
               String saltHex1 = userCredentials1.getSaltHex();
               userDao.changeUserPassword(email, hashPassHex, saltHex1);
               result = true;
            } else {
               result = false;
               //todo redirect on page to enter password one more time
            }
         } else {
            result = false;
            //todo redirect on page such user doesnt exist
         }
      } catch (DaoException e) {
         logger.log(Level.ERROR, "DaoException at change password method. {}", e);
         throw new ServiceException("DaoException at change password method. " + e);
      }
      return result;
   }

}
