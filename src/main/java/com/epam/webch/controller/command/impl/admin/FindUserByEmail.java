package com.epam.webch.controller.command.impl.admin;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.impl.RequestParameter;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.service.user.AdminService;
import com.epam.webch.model.service.user.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.epam.webch.model.entity.user.User.UserRole.admin;

public class FindUserByEmail implements Command {
    private AdminService adminService= AdminServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();

    @AllowedRole({admin})
    @Override
    public Router execute(HttpServletRequest request) {
        String userEmail = request.getParameter(RequestParameter.USER_EMAIL_TO_FIND.getValue());
        Router result;
        try {
            Optional<User> user = adminService.findUserByEmail(userEmail);
            if (user.isPresent()) {
                request.getSession().setAttribute(SessionAttribute.CURRENT_ENTITY_TO_DISPLAY.name(), user.get());
                result = new Router(PagePath.ADMIN_ENTITY_SETTINGS_PAGE.getValue(), Router.RouterType.FORWARD);
            } else {
                result = new Router(PagePath.HOME_ADMIN_PAGE.getValue(), Router.RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Service exception at FindUserByEmail info {}", e);
            result = new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return result;
    }
}
