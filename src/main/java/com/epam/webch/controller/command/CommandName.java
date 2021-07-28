package com.epam.webch.controller.command;

import com.epam.webch.controller.BaseEnum;

import java.util.Optional;

public enum CommandName implements BaseEnum {
    CONTROLLER_PATH("http://localhost:8080/webch_war_exploded/controller"),
    COMMAND("command"),

    CHANGE_LANGUAGE("change-language"),

    OPEN_HOME_PAGE("home"),
    OPEN_SIGN_IN_PAGE("open-sign-in-page"),
    OPEN_SIGN_UP_PAGE("open-sign-up-page"),
    OPEN_PRODUCT_PAGE("open-product-page"),
    OPEN_HOME_USER_PAGE("open-home-user-page"),
    OPEN_HOME_EMPLOYEE_PAGE("open-home-employee-page"),
    OPEN_HOME_ADMIN_PAGE("open-home-admin-page"),
    OPEN_SHOP_PAGE("open-shop-page"),
    OPEN_SETTINGS_PAGE("open-settings-page"),
    OPEN_CART_PAGE("open-cart-page"),
    OPEN_ORDERING_PAGE("open-ordering-page"),
    CREATE_ORDER("create-order"),
    OPEN_SUCCESSFUL_OPERATION_PAGE("open-successful-operation-page"),
    OPEN_ERROR_OPERATION_PAGE("open-error-operation-page"),

    ADD_TO_CART("add-to-cart"),
    DELETE_FROM_CART("delete-from-cart"),
    CHANGE_USER_INFO("change-user-info"),
    SIGN_IN_USER("sign-in-user"),
    SIGN_UP_USER("sign-up-user"),
    SIGN_OUT_USER("sign-out-user"),
    DELETE_USER("delete-user");



    private String value;

    CommandName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<CommandName> defineCommand(String command) {
        for (CommandName commandName : CommandName.values()) {
            if (commandName.value.equals(command.toLowerCase())) {
                return Optional.of(commandName);
            }
        }
        return Optional.empty();
    }
}
