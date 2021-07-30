package com.epam.webch.controller.impl;

import com.epam.webch.controller.BaseEnum;

public enum IncludePagePath implements BaseEnum {
    DEFAULT_NAVIGATION_BAR("templates/default_navigation_bar.jsp"),
    GUEST_NAVIGATION_BAR_FROM_ROOT("../templates/guest_nav_bar.jsp"),
    GUEST_NAVIGATION_BAR("templates/guest_nav_bar.jsp"),
    USER_NAVIGATION_BAR_FROM_ROOT("../templates/user_navigation_bar.jsp"),
    USER_NAVIGATION_BAR("templates/user_navigation_bar.jsp"),
    FORM_SIGN_IN("templates/form/sign_in_form.jsp"),
    FORM_SIGN_UP("templates/form/sign_up_form.jsp"),
    FORM_SETTINGS("templates/form/settings_form.jsp"),
    FORM_PRODUCT("templates/form/product_form.jsp"),
    FORM_PRODUCT_FROM_ROOT("../templates/form/product_form.jsp"),
    FORM_CART_PRODUCT("templates/form/product_cart_form.jsp"),
    FORM_CART_PRODUCT_FROM_ROOT("../templates/form/product_cart_form.jsp"),
    FORM_ORDER("templates/form/order_form.jsp"),
    FORM_PRODUCT_ADMIN_LIST("../templates/form/product_admin_list_form.jsp"),
    FORM_ENTITY_ADMIN_LIST("../templates/form/entity_list_form.jsp"),
    FORM_ORDER_ADMIN_LIST("../templates/form/order_list_form.jsp"),
    FORM_PRODUCT_SETTINGS_PAGE("../templates/form/product_settings_form.jsp"),
    FORM_ENTITY_SETTINGS_PAGE("../templates/form/entity_settings_form.jsp"),

    CSS_FORM_ORDER("css/order_form.css"),
    CSS_SETTINGS_FORM("css/settings_form.css"),
    CSS_SIGN_IN_FORM("css/sign_in_form.css"),
    CSS_SIGN_UP_FORM("css/sign_up_form.css"),
    CSS_NAV_BAR("css/nav_bar.css"),
    CSS_HOME_USER_PAGE("css/home_user_page.css"),
    CSS_HOME_PAGE("css/home_page.css"),
    CSS_FORM_PRODUCT("css/product_form.css"),
    CSS_SUCCESS_OPERATION_PAGE("css/success_operation_page.css"),
    CSS_ADMIN_HOME_PAGE("css/admin_home_page.css");

    private String value;

    IncludePagePath(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
