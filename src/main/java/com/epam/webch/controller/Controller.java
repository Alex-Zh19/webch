package com.epam.webch.controller;


import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.CommandFactory;
import com.epam.webch.controller.command.Router;
import com.epam.webch.model.connection.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private static final Logger logger= LogManager.getLogger();
    private static final String COMMAND="command";
    @Override
    public void init() {
        ConnectionPool.getInstance();
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().deleteConnectionPool();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandFromPage = request.getParameter(COMMAND);
        Command command = CommandFactory.createCommand(commandFromPage);
        Router router = command.execute(request);
        switch (router.getRouterType()) {
            case FORWARD:
                request.getRequestDispatcher(router.getPathToNextPage()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(router.getPathToNextPage());
                break;
            default:
                logger.log(Level.ERROR, "unknown routerType exception");
                //todo redirect to error page
        }
    }
}