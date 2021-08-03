package com.epam.webch.controller;

import com.epam.webch.controller.command.Command;
import com.epam.webch.model.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;

public class AllowedRoleChecker {
    private static final String METHOD_NAME = "execute";

    private AllowedRoleChecker() {
    }
    public static boolean isRoleAllowed(Command command, HttpServletRequest request) {
        User.UserRole currentRole = (User.UserRole) request.getSession().
                getAttribute(SessionAttribute.CURRENT_USER_ROLE.name());
        Class<? extends Command> checkedClass = command.getClass();
        try {
            Method checkedClassMethod = checkedClass.getMethod(METHOD_NAME, HttpServletRequest.class);
            User.UserRole[] rolesAllowed = checkedClassMethod.getAnnotation(AllowedRole.class).value();
            for (User.UserRole role : rolesAllowed) {
                if (currentRole == role) {
                    return true;
                }
            }
        } catch (NoSuchMethodException e) {
            //empty catch because in this case method also return false and user will be
            //redirected to DeniedAccessPage
        }
        return false;
    }
}
