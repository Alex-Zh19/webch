package com.epam.webch.controller;

import com.epam.webch.model.entity.user.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface AllowedRole {
    User.UserRole[]value() default User.UserRole.guest;
}
