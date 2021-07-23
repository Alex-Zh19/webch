package com.epam.webch.controller.command;

import java.util.Optional;

public enum CommandName {
    CONTROLLER_PATH_AND_COMMAND("http://localhost:8080/webprojectch_war_exploded/controller?command="),

    CHANGE_LANGUAGE("change-language"),



    OPEN_HOME_PAGE("home"),// OPEN_FIRST_PAGE("open-first-page"),//

    OPEN_SIGN_IN_PAGE("open-sign-in-page"),
    OPEN_SIGN_UP_PAGE("open-sign-up-page"),

    OPEN_HOME_GUEST_PAGE("open-home-guest-page"),
    OPEN_HOME_USER_PAGE("open-home-user-page"),
    OPEN_SHOP_PAGE("open-shop-page"),
    OPEN_SETTINGS_PAGE("open-settings-page"),
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