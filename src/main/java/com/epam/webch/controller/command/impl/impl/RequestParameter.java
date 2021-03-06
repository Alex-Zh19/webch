package com.epam.webch.controller.command.impl.impl;

import com.epam.webch.controller.command.BaseType;

public enum RequestParameter implements BaseType {


    NAME("name"),
    SURNAME("surname"),
    EMAIL("email"),
    PASSWORD("password"),
    CURRENT_PASSWORD("current-password"),
    NEW_PASSWORD("new-password"),
    CONFIRM_NEW_PASSWORD("confirm-new-password"),
    CONFIRM_PASSWORD("confirm-password"),
    NEW_EMAIL("new-email"),
    PRODUCT_ID("product-id"),
    PRODUCT("product"),
    LANGUAGE("language"),
    RUSSIAN_LANGUAGE("Russian"),
    ENGLISH_LANGUAGE("English"),
    ADDRESS_ORDER_CREATOR("address"),
    NAME_OF_ORDER_CREATOR("name-of-order-creator"),
    SURNAME_OF_ORDER_CREATOR("surname-of-order-creator"),
    DATE("date"),
    ORDER_NUMBER("order-number"),
    ENTITY_ID("entity-id"),
    PRODUCT_NAME("product-name"),
    PRODUCT_PRICE("product-price"),
    PRODUCT_IN_STOCK("product-in-stock"),
    PRODUCT_DESCRIPTION("product-description"),
    USER_TO_CHANGE("user-to-change"),
    USER_ROLE_TO_CHANGE("user-role-to-change"),
    USER_STATUS_TO_CHANGE("user-status-to-change"),
    ORDER_STATUS_TO_CHANGE("order-status-to-change"),
    USER_EMAIL_TO_FIND("user-email-to-find");

    private String value;

    RequestParameter(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
