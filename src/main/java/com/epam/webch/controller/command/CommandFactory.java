package com.epam.webch.controller.command;

import com.epam.webch.controller.command.impl.*;
import com.epam.webch.controller.command.impl.guest.OpenHomeGuestPage;
import com.epam.webch.controller.command.impl.locale.ChangeLocaleCommand;
import com.epam.webch.controller.command.impl.user.ChangeUserInfo;
import com.epam.webch.controller.command.impl.user.DeleteUser;
import com.epam.webch.controller.command.impl.user.OpenHomeUserPage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CommandFactory {
    private static final Logger logger = LogManager.getLogger();

    public static Command createCommand(String commandFromPage) {
        Optional<CommandName> commandName = CommandName.defineCommand(commandFromPage);
        Command command;
        if (commandName.isPresent()) {
            command = switch (commandName.get()) {
                case OPEN_HOME_PAGE -> new OpenHomePage();
                case OPEN_SIGN_IN_PAGE -> new OpenSignInPage();
                case OPEN_SIGN_UP_PAGE -> new OpenSignUpPage();
                case OPEN_HOME_GUEST_PAGE -> new OpenHomeGuestPage();
                case CHANGE_LANGUAGE -> new ChangeLocaleCommand();
                case SIGN_IN_USER -> new SignInUser();
                case SIGN_UP_USER -> new SignUpUser();
                case SIGN_OUT_USER -> new SignOutUser();
                case DELETE_USER -> new DeleteUser();
                case OPEN_HOME_USER_PAGE -> new OpenHomeUserPage();
                case OPEN_SETTINGS_PAGE -> new OpenSettingsPage();
                case CHANGE_USER_INFO -> new ChangeUserInfo();
                case OPEN_SHOP_PAGE -> new OpenShopPage();
                case OPEN_PRODUCT_PAGE -> new OpenProductPage();
                case ADD_TO_CART -> new AddToCart();
                default -> throw new UnsupportedOperationException("such command doesnt exist");
            };
        } else {
            logger.log(Level.ERROR, "such command doesnt exist");
            throw new IllegalArgumentException("command value doesnt exist");
        }
        return command;
    }
}
