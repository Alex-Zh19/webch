package com.epam.webch.controller.command.impl.user;

import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FindOrderByCreator implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
