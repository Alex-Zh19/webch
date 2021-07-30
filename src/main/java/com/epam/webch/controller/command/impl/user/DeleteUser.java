package com.epam.webch.controller.command.impl.user;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.service.user.UserService;
import com.epam.webch.model.service.user.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DeleteUser implements Command {
    private final UserService userService= UserServiceImpl.getInstance();
    private static final Logger logger= LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        User currentUser=(User)request.getSession().getAttribute(SessionAttribute.CURRENT_USER.name());
        try{
            if(userService.signOutUser(currentUser.getId())){
                return new Router(PagePath.HOME_PAGE.getValue(), Router.RouterType.FORWARD);
            }
        }catch (ServiceException e){
            logger.log(Level.ERROR,"service exception at DeleteUser");
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
