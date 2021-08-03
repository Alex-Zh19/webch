package com.epam.webch.controller;

import com.epam.webch.model.entity.user.User;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static com.epam.webch.model.entity.user.User.UserRole.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface AllowedRole {
    User.UserRole[]value() default {guest,user,employee,admin};
}
