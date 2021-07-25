package com.epam.webch.controller.command.impl;

import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddToCart implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
