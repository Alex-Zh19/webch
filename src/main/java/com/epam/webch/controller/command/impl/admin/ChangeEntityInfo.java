package com.epam.webch.controller.command.impl.admin;

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
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ChangeEntityInfo implements Command {
    private static final Logger logger = LogManager.getLogger();
    private AdminService adminService = AdminServiceImpl.getInstance();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        PagePath prevPage = (PagePath) request.getSession().getAttribute(SessionAttribute.PREVIOUS_PAGE.name());
        Optional<String> role = Optional.ofNullable(request.getParameter(RequestParameter.USER_ROLE_TO_CHANGE.getValue()));
        Optional<String> status = Optional.ofNullable(request.getParameter(RequestParameter.USER_STATUS_TO_CHANGE.getValue()));
        Optional<User> optionalUser;
        String stringId = request.getParameter(RequestParameter.ENTITY_ID.getValue());
        Long entityId = Long.parseLong(stringId);
        try {
            optionalUser = adminService.findUserById(entityId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Service exception at ChangeEntityInfo info {}", e);
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String email=user.getEmail();
            if (!role.isEmpty() && !(user.getUserRole()== User.UserRole.valueOf(role.get()))) {
                try {
                    adminService.changeUserRole(email, User.UserRole.valueOf(role.get()));
                    request.setAttribute(RequestParameter.USER_TO_CHANGE.name(), user);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, "Service exception at ChangeEntityInfo info {}", e);
                    return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
                }
            }
            if (!status.isEmpty() && !(user.getUserStatus() == User.UserStatus.valueOf(status.get()))) {
                try {
                    adminService.changeUserStatus(email, User.UserStatus.valueOf(status.get()));
                    request.setAttribute(RequestParameter.USER_TO_CHANGE.name(), user);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, "Service exception at ChangeEntityInfo info {}", e);
                    return new Router(PagePath.ERROR_OPERATION_PAGE.getValue(), Router.RouterType.FORWARD);
                }
            }
        }
        return new Router(PagePath.ADMIN_ENTITY_SETTINGS_PAGE.getValue(), Router.RouterType.FORWARD);

    }
}
