package com.epam.webch.model.localization;

import com.epam.webch.controller.SessionAttribute;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager extends ResourceBundle {
    private static final String TEXT_BASE_NAME = "\\localization\\localization";
    private static final Locale DEFAULT_LOCALE = LocaleAttribute.EN.getLocale();

    private LocalizationManager(Locale locale) {
        setLocale(locale);
    }

    public static void setDefaultManager(HttpServletRequest request) {
        if (request.getSession().getAttribute(SessionAttribute.TEXT.name()) == null) {
            request.getSession().setAttribute(SessionAttribute.TEXT.name(),
                    new LocalizationManager(DEFAULT_LOCALE));
            setSessionVariables(request.getServletContext());
        }
    }

    public static void setDefaultManager(HttpSession session) {
        if (session.getAttribute(SessionAttribute.TEXT.name()) == null) {
            session.setAttribute(SessionAttribute.TEXT.name(),
                    new LocalizationManager(DEFAULT_LOCALE));
            setSessionVariables(session.getServletContext());
        }
    }

    public static LocalizationManager getCurrentInstance(HttpServletRequest request) {
        return (LocalizationManager) request.getSession().getAttribute(SessionAttribute.TEXT.name());
    }

    public void setLocale(Locale locale) {
        if (parent == null || !parent.getLocale().equals(locale)) {
            setParent(getBundle(TEXT_BASE_NAME, locale));
        }
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }

    @Override
    protected Object handleGetObject(String key) {
        return parent.getObject(key);
    }

    private static void setSessionVariables(ServletContext servletContext){
        setEnumVariables(servletContext,LocaleKey.values());
    }

    private static <T extends Enum<?>> void setEnumVariables(ServletContext servletContext, T[]enumVar){
        for(T var:enumVar){
            String enumElName=var.name();
            LocaleKey localeKey=(LocaleKey) var;
            servletContext.setAttribute(enumElName,localeKey.getValue());
        }
    }

}
