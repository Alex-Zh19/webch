package com.epam.webch.controller.command.impl.admin;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OpenChangeOrderInfoPage implements Command {//todo
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return new Router(PagePath.);
    }
}
