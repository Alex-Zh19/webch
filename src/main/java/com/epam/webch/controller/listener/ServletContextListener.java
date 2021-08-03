package com.epam.webch.controller.listener;

import com.epam.webch.controller.command.BaseType;
import com.epam.webch.controller.command.CommandName;
import com.epam.webch.controller.impl.IncludePagePath;
import com.epam.webch.controller.impl.RequestParameter;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.user.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletContextListener implements jakarta.servlet.ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        jakarta.servlet.ServletContextListener.super.contextInitialized(sce);
        setEnumVariables(sce.getServletContext(), IncludePagePath.values());
        setEnumVariables(sce.getServletContext(), RequestParameter.values());
        setEnumVariables(sce.getServletContext(), CommandName.values());
        setEnumVariables(sce.getServletContext(), User.UserRole.values());
        setEnumVariables(sce.getServletContext(), Order.OrderStatus.values());
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        jakarta.servlet.ServletContextListener.super.contextDestroyed(sce);
    }

    private <T extends Enum<?>> void setEnumVariables(ServletContext servletContext, T[] enumVar) {
        for (T var : enumVar) {
            String enumElName = var.name();
            BaseType enumVarEl = (BaseType) var;
            servletContext.setAttribute(enumElName, enumVarEl.getValue());
        }
    }
}
