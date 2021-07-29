package com.epam.webch.controller.command.impl.employee;

import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeOrderStatus implements Command {//order list form
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
