package com.epam.webch.controller.command.impl.locale;

import com.epam.webch.controller.AllowedRole;
import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.controller.impl.RequestParameter;
import com.epam.webch.model.util.localization.LocaleAttribute;
import com.epam.webch.model.util.localization.LocalizationManager;
import jakarta.servlet.http.HttpServletRequest;

import static com.epam.webch.model.entity.user.User.UserRole.*;


public class ChangeLocaleCommand implements Command {
    @AllowedRole
    @Override
    public Router execute(HttpServletRequest request) {
        PagePath path = (PagePath) request.getSession().getAttribute(SessionAttribute.PREVIOUS_PAGE.name());
        Router router = new Router(path.getValue(), Router.RouterType.FORWARD);
        String requestedLocale = request.getParameter(RequestParameter.LANGUAGE.getValue());
        LocaleAttribute localeAttribute = LocaleAttribute.defineLocale(requestedLocale);
        LocalizationManager.getCurrentInstance(request).setLocale(localeAttribute.getLocale());
        return router;
    }

}
