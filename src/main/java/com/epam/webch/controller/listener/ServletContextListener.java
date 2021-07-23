package com.epam.webch.controller.listener;

import com.epam.webch.controller.BaseEnum;
import com.epam.webch.controller.impl.IncludePagePath;
import com.epam.webch.controller.impl.RequestParameter;
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
        System.out.println("ok");
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        jakarta.servlet.ServletContextListener.super.contextDestroyed(sce);
    }

    private <T extends Enum<?>> void setEnumVariables(ServletContext servletContext, T[] enumVar) {
        for (T var : enumVar) {
            String enumElName = var.name();
            BaseEnum enumVarEl = (BaseEnum) var;
            servletContext.setAttribute(enumElName, enumVarEl.getValue());
        }
    }
}
