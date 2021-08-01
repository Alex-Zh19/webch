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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OpenEmployeeList implements Command {
    private AdminService adminService = AdminServiceImpl.getInstance();
    private static final Logger logger= LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(SessionAttribute.PREVIOUS_PAGE.name(), PagePath.HOME_ADMIN_PAGE);
        List<User> employeeList = new ArrayList<>();
        try {
            List<Optional<User>> list = adminService.findAllEmployees();
            for (Optional<User> employee : list) {
                if (employee.isPresent()) {
                    employeeList.add(employee.get());
                }
            }
            request.setAttribute(RequestParameter.ENTITY_LIST.name(), employeeList);
        } catch (ServiceException e) {
            logger.log(Level.ERROR,"service exception at OpenEmployeeList");
            return new Router(PagePath.ERROR_404_PAGE.getValue(), Router.RouterType.FORWARD);
        }
        return new Router(PagePath.ADMIN_ENTITY_LIST_PAGE.getValue(), Router.RouterType.FORWARD);
    }
}
