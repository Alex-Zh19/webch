package com.epam.webch.controller.command.impl.locale;

import com.epam.webch.controller.PagePath;
import com.epam.webch.controller.SessionAttribute;
import com.epam.webch.controller.command.Command;
import com.epam.webch.controller.command.Router;
import com.epam.webch.model.localization.LocaleAttribute;
import com.epam.webch.model.localization.LocalizationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.epam.webch.controller.PagePath.HOME_PAGE;

public class ChangeLocaleCommand implements Command {
    private final String LANGUAGE="language";
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        PagePath path=(PagePath) request.getSession().getAttribute(SessionAttribute.PREVIOUS_PAGE.name());
        String requestedLocale = request.getParameter(LANGUAGE);
        LocaleAttribute localeAttribute = LocaleAttribute.defineLocale(requestedLocale);
        LocalizationManager.getCurrentInstance(request).setLocale(localeAttribute.getLocale());
        return new Router(path.getValue(), Router.RouterType.FORWARD);
    }
}
