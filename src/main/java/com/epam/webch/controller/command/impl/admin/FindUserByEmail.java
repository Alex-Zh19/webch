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

public class FindUserByEmail implements Command {
    private AdminService adminService= AdminServiceImpl.getInstance();
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String userEmail=request.getParameter(RequestParameter.EMAIL.getValue());
        try{
            Optional<User> user=adminService.findUserByEmail(userEmail);
            if(user.isPresent()){
                request.setAttribute(RequestParameter.USER_TO_FIND.name(), user.get());
            }
        }catch (ServiceException e){
            logger.log(Level.ERROR, "Service exception at FindUserByEmail info {}", e);
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.USER_INFORMATION_FOR_ADMIN_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
