package com.epam.webch.controller.command.impl.all;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.command.impl.impl.RequestParameter;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.service.user.UserService;
import com.epam.webch.model.service.user.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class SignInUser implements Command {
    private static final Logger logger= LogManager.getLogger();
    private final UserService userService = UserServiceImpl.getInstance();

    @AllowedRole
    @Override
    public Router execute(HttpServletRequest request) {
        String baseEnum = (String) request.getServletContext().getAttribute(RequestParameter.EMAIL.getValue());
        System.out.println(baseEnum);
        Router router;
        PagePath prevPage = PagePath.SIGN_IN_PAGE;
        String email = request.getParameter(RequestParameter.EMAIL.getValue());
        String password = request.getParameter(RequestParameter.PASSWORD.getValue());
        System.out.println(email+"  "+password);
        if (email != null && password != null) {
            try {
                Optional<User> user = userService.signInUser(email, password);
                if (user.isPresent()) {
                    request.getSession().setAttribute(SessionAttribute.CURRENT_USER_ROLE.name(), user.get().getUserRole());
                    request.getSession().setAttribute(SessionAttribute.CURRENT_USER.name(), user.get());
                    request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.HOME_USER_PAGE);

                    if(user.get().getUserStatus()== User.UserStatus.blocked||
                            user.get().getUserStatus()== User.UserStatus.deleted){
                        router = new Router(PagePath.BAN_PAGE.getValue(), Router.RouterType.FORWARD);
                    }else if(user.get().getUserRole()== User.UserRole.admin){
                        router = new Router(PagePath.HOME_ADMIN_PAGE.getValue(), Router.RouterType.FORWARD);
                    }else if(user.get().getUserRole()== User.UserRole.employee){
                        router = new Router(PagePath.HOME_USER_PAGE.getValue(), Router.RouterType.FORWARD);
                    }else {
                        router = new Router(PagePath.HOME_USER_PAGE.getValue(), Router.RouterType.FORWARD);
                    }

                } else {
                    router = new Router(prevPage.getValue(), Router.RouterType.FORWARD);
                }

            } catch (ServiceException e) {
                logger.log(Level.ERROR,"service exception at SignInUser");
                router = new Router(prevPage.getValue(), Router.RouterType.FORWARD);
            }
        } else {
            logger.log(Level.ERROR,"service exception at SignInUser");
            router = new Router(prevPage.getValue(), Router.RouterType.FORWARD);
        }
        return router;
    }
}

