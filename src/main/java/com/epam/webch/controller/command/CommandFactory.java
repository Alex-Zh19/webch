package com.epam.webch.controller.command;

import com.epam.webch.controller.command.impl.admin.*;
import com.epam.webch.controller.command.impl.all.*;
import com.epam.webch.controller.command.impl.employee.ChangeOrderInfo;
import com.epam.webch.controller.command.impl.employee.OpenOrdersList;
import com.epam.webch.controller.command.impl.locale.ChangeLocaleCommand;
import com.epam.webch.controller.command.impl.user.ChangeUserInfo;
import com.epam.webch.controller.command.impl.user.DeleteUser;
import com.epam.webch.controller.command.impl.user.FindOrderByCreator;
import com.epam.webch.controller.command.impl.user.SignOutUser;
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
                case CHANGE_LANGUAGE -> new ChangeLocaleCommand();
                case SIGN_IN_USER -> new SignInUser();
                case SIGN_UP_USER -> new SignUpUser();
                case SIGN_OUT_USER -> new SignOutUser();
                case DELETE_USER -> new DeleteUser();
                case OPEN_SETTINGS_PAGE -> new OpenSettingsPage();
                case CHANGE_USER_INFO -> new ChangeUserInfo();
                case OPEN_SHOP_PAGE -> new OpenShopPage();
                case OPEN_PRODUCT_PAGE -> new OpenProductPage();
                case ADD_TO_CART -> new AddToCart();
                case OPEN_CART_PAGE -> new OpenCartPage();
                case DELETE_FROM_CART -> new DeleteFromCart();
                case OPEN_ORDERING_PAGE -> new OpenOrderingPage();
                case CREATE_ORDER -> new CreateOrder();
                case OPEN_SUCCESSFUL_OPERATION_PAGE -> new OpenSuccessfulOperationPage();
                case OPEN_ERROR_OPERATION_PAGE -> new OpenErrorOperationPage();
                case OPEN_EMPLOYEE_LIST -> new OpenEmployeeList();
                case OPEN_USERS_LIST -> new OpenUsersList();
                case OPEN_ORDERS_LIST -> new OpenOrdersList();
                case OPEN_PRODUCTS_LIST -> new OpenProductsList();
                case OPEN_CHANGE_PRODUCT_INFO_PAGE -> new OpenChangeProductInfoPage();
                case OPEN_CHANGE_ENTITY_INFO_PAGE -> new OpenChangeEntityInfoPage();
                case CHANGE_PRODUCT_INFO -> new ChangeProductInfo();
                case CHANGE_ENTITY_INFO -> new ChangeEntityInfo();
                case CHANGE_ORDER_INFO -> new ChangeOrderInfo();
                case FIND_ORDER_BY_CREATOR -> new FindOrderByCreator();
                case FIND_USER_BY_EMAIL -> new FindUserByEmail();
                default -> throw new UnsupportedOperationException("such command doesnt exist");
            };
        } else {
            logger.log(Level.ERROR, "such command doesnt exist");
            throw new IllegalArgumentException("command value doesnt exist");
        }
        return command;
    }
}
