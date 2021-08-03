package com.epam.webch.controller.command.impl.all;

import com.epam.webch.controller.AllowedRole;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.epam.webch.model.entity.user.User.UserRole.*;
import static com.epam.webch.model.entity.user.User.UserRole.admin;

public class SignUpUser implements Command {
    private final int DEFAULT_BALANCE = 0;
    private final User.UserStatus DEFAULT_STATUS = User.UserStatus.active;
    private final User.UserRole DEFAULT_ROLE = User.UserRole.user;
    private final UserService userService = UserServiceImpl.getInstance();
    private static final Logger logger= LogManager.getLogger();
    @AllowedRole
    @Override
    public Router execute(HttpServletRequest request) {
        PagePath prevPage = (PagePath) request.getSession().getAttribute(SessionAttribute.PREVIOUS_PAGE.name());
        String email = request.getParameter(RequestParameter.EMAIL.getValue());
        String password = request.getParameter(RequestParameter.PASSWORD.getValue());
        String confirmPassword = request.getParameter(RequestParameter.CONFIRM_PASSWORD.getValue());
        String name = request.getParameter(RequestParameter.NAME.getValue());
        Optional<String> surname = Optional.ofNullable(request.getParameter(RequestParameter.SURNAME.getValue()));
        try {
            if (userService.signUpUser(email, password, confirmPassword, name, surname)) {
                request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.SIGN_IN_PAGE);
                return new Router(PagePath.SIGN_IN_PAGE.getValue(), Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"service exception at SignUpUser");
            return new Router(prevPage.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(prevPage.getValue(), Router.RouterType.FORWARD);
    }
}
