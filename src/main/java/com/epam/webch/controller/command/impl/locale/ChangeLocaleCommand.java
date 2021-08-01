package com.epam.webch.controller.command.impl.locale;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.CommandFactory;
import com.epam.webch.controller.command.CommandName;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.impl.RequestParameter;
import com.epam.webch.model.util.localization.LocaleAttribute;
import com.epam.webch.model.util.localization.LocalizationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        PagePath path = (PagePath) request.getSession().getAttribute(SessionAttribute.PREVIOUS_PAGE.name());
        Router router;
        if (path == PagePath.SHOP_PAGE) {
            router = new Router(path.getValue(), Router.RouterType.FORWARD,CommandName.OPEN_SHOP_PAGE);
        } else if(path == PagePath.ADMIN_ENTITY_LIST_PAGE){
            router = new Router(path.getValue(), Router.RouterType.FORWARD,CommandName.OPEN_HOME_PAGE);
        }else if(path == PagePath.ADMIN_PRODUCT_LIST_PAGE){
            router = new Router(path.getValue(), Router.RouterType.FORWARD,CommandName.OPEN_PRODUCTS_LIST);
        }else if(path == PagePath.ADMIN_EMPLOYEE_ORDER_LIST_PAGE){
            router = new Router(path.getValue(), Router.RouterType.FORWARD,CommandName.OPEN_ORDERS_LIST);
        }else if(path == PagePath.HOME_ADMIN_PAGE||path == PagePath.HOME_EMPLOYEE_PAGE||
                path == PagePath.HOME_USER_PAGE||path == PagePath.HOME_PAGE){
            router = new Router(path.getValue(), Router.RouterType.FORWARD,CommandName.OPEN_HOME_PAGE);
        }else if(path == PagePath.GUEST_CART_PAGE||path == PagePath.USER_CART_PAGE){
            router = new Router(path.getValue(), Router.RouterType.FORWARD,CommandName.OPEN_CART_PAGE);
        }else {
            router = new Router(path.getValue(), Router.RouterType.FORWARD);
        }
        String requestedLocale = request.getParameter(RequestParameter.LANGUAGE.getValue());
        LocaleAttribute localeAttribute = LocaleAttribute.defineLocale(requestedLocale);
        LocalizationManager.getCurrentInstance(request).setLocale(localeAttribute.getLocale());
        return router;
    }

}
