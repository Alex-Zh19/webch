package com.epam.webch.controller;

public enum PagePath {
    HOME_PAGE("WEB-INF/jsp/all/home_page.jsp"),
    SIGN_IN_PAGE("WEB-INF/jsp/all/sign_in_page.jsp"),
    SIGN_UP_PAGE("WEB-INF/jsp/all/sign_up_page.jsp"),
    HOME_USER_PAGE("WEB-INF/jsp/user/user_home_page.jsp"),
    HOME_ADMIN_PAGE("WEB-INF/jsp/admin/admin_home_page.jsp"),
    HOME_EMPLOYEE_PAGE("WEB-INF/jsp/employee/employee_home_page.jsp"),
    SHOP_PAGE("WEB-INF/jsp/all/shop_page.jsp"),
    SETTINGS_PAGE("WEB-INF/jsp/all/settings_page.jsp"),
    GUEST_CART_PAGE("WEB-INF/jsp/guest/cart_page.jsp"),
    USER_CART_PAGE("WEB-INF/jsp/user/cart_page.jsp"),
    ORDERING_PAGE("WEB-INF/jsp/all/ordering_page.jsp"),
    SUCCESSFUL_OPERATION_PAGE("WEB-INF/jsp/all/success_operation_page.jsp"),
    PRODUCT_PAGE("WEB-INF/jsp/all/product_page.jsp"),
    ADMIN_PRODUCT_LIST_PAGE("WEB-INF/jsp/admin/product_list_page.jsp"),
    ADMIN_ENTITY_LIST_PAGE("WEB-INF/jsp/admin/entity_list_page.jsp"),
    ADMIN_EMPLOYEE_ORDER_LIST_PAGE("WEB-INF/jsp/employee/order_list_page.jsp"),
    USER_ORDER_LIST_PAGE("WEB-INF/jsp/user/user_order_list_page.jsp"),
    ADMIN_PRODUCT_SETTINGS_PAGE("WEB-INF/jsp/admin/product_settings_page.jsp"),
    ADMIN_ENTITY_SETTINGS_PAGE("WEB-INF/jsp/admin/entity_settings_page.jsp"),
    USER_INFORMATION_FOR_ADMIN_PAGE("WEB-INF/jsp/admin/user_information_for_admin_page.jsp"),

    ERROR_OPERATION_PAGE("WEB-INF/error_page/error_operation_page.jsp"),
    ERROR_404_PAGE("WEB-INF/error_page/error_404.jsp"),
    BAN_PAGE("WEB-INF/error_page/ban_page.jsp"),
    DENIED_ACCESS_PAGE("WEB-INF/error_page/denied_access_page.jsp");


    private String value;

    PagePath(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
