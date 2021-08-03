package com.epam.webch.controller.command.impl.admin;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.user.User;
import com.epam.webch.model.service.user.AdminService;
import com.epam.webch.model.service.user.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.webch.model.entity.user.User.UserRole.admin;

public class OpenEmployeeList implements Command {
    private AdminService adminService = AdminServiceImpl.getInstance();
    private static final Logger logger= LogManager.getLogger();
    @AllowedRole({admin})
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.ADMIN_ENTITY_LIST_PAGE);
        List<User> employeeList = new ArrayList<>();
        try {
            List<Optional<User>> list = adminService.findAllEmployees();
            for (Optional<User> employee : list) {
                if (employee.isPresent()) {
                    employeeList.add(employee.get());
                }
            }
            request.getSession().setAttribute(SessionAttribute.CURRENT_ENTITY_TO_DISPLAY.name(), employeeList);
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"service exception at OpenEmployeeList");
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ADMIN_ENTITY_LIST_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
