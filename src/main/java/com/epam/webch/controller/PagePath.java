package com.epam.webch.controller;

public enum PagePath {


    HOME_PAGE("WEB-INF/jsp/home_page.jsp"),
    SIGN_IN_PAGE("WEB-INF/jsp/sign_in_page.jsp"),
    SIGN_UP_PAGE("WEB-INF/jsp/sign_up_page.jsp"),
    HOME_GUEST_PAGE("WEB-INF/jsp/guest/home_page.jsp"),
    HOME_USER_PAGE("WEB-INF/jsp/user/home_page.jsp"),
    SHOP_PAGE("WEB-INF/jsp/shop_page.jsp"),
    ERROR_404_PAGE("WEB-INF/error_page/error_404.jsp"),
    SETTINGS_PAGE("WEB-INF/jsp/settings_page.jsp"),
    GUEST_CART_PAGE("WEB-INF/jsp/guest/cart_page.jsp"),
    USER_CART_PAGE("WEB-INF/jsp/user/cart_page.jsp"),
    ORDERING_PAGE("WEB-INF/jsp/ordering_page.jsp"),
    SUCCESSFUL_OPERATION_PAGE("WEB-INF/jsp/success_operation_page.jsp"),
    ERROR_OPERATION_PAGE("WEB-INF/jsp/error_operation_page.jsp"),
    HELLO_PAGE("WEB-INF/jsp/hello.jsp"),
    FIRST_PAGE("WEB-INF/jsp/page.jsp"),
    USER_NOT_FOUND("WEB-INF/jsp/userNotFound.jsp");


    private String value;

    PagePath(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
