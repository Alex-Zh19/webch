package com.epam.webch.controller.impl;

import com.epam.webch.controller.BaseEnum;

public enum RequestParameter implements BaseEnum {


    NAME("name"),
    SURNAME("surname"),
    EMAIL("email"),
    PASSWORD("password"),
    CURRENT_PASSWORD("current-password"),
    NEW_PASSWORD("new-password"),
    CONFIRM_NEW_PASSWORD("confirm-new-password"),
    CONFIRM_PASSWORD("confirm-password"),
    NEW_EMAIL("new-email"),
    PRODUCT_LIST("product-list"),
    PRODUCT_ID("product-id"),
    LANGUAGE("language"),
    RUSSIAN_LANGUAGE("Russian"),
    ENGLISH_LANGUAGE("English"),
    DETAILS("details"),
    DATE("date");

    private String value;

    RequestParameter(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
