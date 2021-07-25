package com.epam.webch.controller.impl;

import com.epam.webch.controller.BaseEnum;

public enum IncludePagePath implements BaseEnum {
    DEFAULT_NAVIGATION_BAR("templates/default_navigation_bar.jsp"),
    NAVIGATION_BAR("../templates/navigation_bar.jsp"),
    NAVIGATION_BAR_FROM_ROOT("templates/navigation_bar.jsp"),
    FORM_SIGN_IN("templates/form/sign_in_form.jsp"),
    FORM_SIGN_UP("templates/form/sign_up_form.jsp"),
    FORM_SETTINGS("templates/form/settings_form.jsp"),
    FORM_PRODUCT("templates/form/product_form.jsp"),

    CSS_SETTINGS_FORM("css/settings_form.css"),
    CSS_SIGN_IN_FORM("css/sign_in_form.css"),
    CSS_SIGN_UP_FORM("css/sign_up_form.css"),
    CSS_NAV_BAR("css/nav_bar.css"),
    CSS_HOME_USER_PAGE("css/home_user_page.css"),
    CSS_HOME_PAGE("css/home_page.css"),
    CSS_FORM_PRODUCT("css/product_form.css");

    private String value;

    IncludePagePath(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
