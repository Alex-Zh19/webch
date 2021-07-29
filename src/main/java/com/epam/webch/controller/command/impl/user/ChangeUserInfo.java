package com.epam.webch.controller.command.impl.user;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.impl.RequestParameter;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.service.user.UserService;
import com.epam.webch.model.service.user.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ChangeUserInfo implements Command {
    private UserService userService = UserServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        PagePath prevPage = (PagePath) request.getSession().getAttribute(SessionAttribute.PREVIOUS_PAGE.name());
        Optional<String> name = Optional.ofNullable(request.getParameter(RequestParameter.NAME.getValue()));
        Optional<String> surname = Optional.ofNullable(request.getParameter(RequestParameter.SURNAME.getValue()));
        Optional<String> email = Optional.ofNullable(request.getParameter(RequestParameter.EMAIL.getValue()));
        Optional<String> currentPassword = Optional.ofNullable(request.getParameter(RequestParameter.CURRENT_PASSWORD.getValue()));
        Optional<String> newPassword =Optional.ofNullable( request.getParameter(RequestParameter.NEW_PASSWORD.getValue()));
        Optional<String> confirmNewPassword = Optional.ofNullable(request.getParameter(RequestParameter.CONFIRM_NEW_PASSWORD.getValue()));

        User user = (User) request.getSession().getAttribute(SessionAttribute.CURRENT_USER.name());

        if (!user.getEmail().equals(email)&& !email.isEmpty()) {
            try {
                userService.changeUserEmail(user.getEmail(), email.get());
                user.setEmail(email.get());
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Service exception at changeUser info {}", e);
                return new Router(prevPage.getValue(), Router.RouterType.FORWARD);
            }
        }
        if (!user.getName().equals(name)&&!name.isEmpty()) {
            try {
                userService.changeUserName(user.getEmail(), name.get());
                user.setName(name.get());
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Service exception at changeUser info {}", e);
                return new Router(prevPage.getValue(), Router.RouterType.FORWARD);
            }
        }
        if (!user.getSurname().equals(surname)&&!surname.isEmpty()) {
            try {
                userService.changeUserSurname(user.getEmail(), surname.get());
                user.setSurname(surname.get());
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Service exception at changeUser info {}", e);
                return new Router(prevPage.getValue(), Router.RouterType.FORWARD);
            }
        }
        if (!currentPassword.isEmpty() && !newPassword.isEmpty()&& !confirmNewPassword.isEmpty()) {
            try {
                userService.changeUserPassword(user.getEmail(), currentPassword.get(), newPassword.get(),
                        confirmNewPassword.get());
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Service exception at changeUser info {}", e);
                return new Router(prevPage.getValue(), Router.RouterType.FORWARD);
            }
        }
        return new Router(PagePath.HOME_USER_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
