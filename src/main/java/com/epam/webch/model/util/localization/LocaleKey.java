package com.epam.webch.model.util.localization;

public enum LocaleKey {
    PAGE_HELLO("page_hello"),
    LANGUAGE("language"),
    LANGUAGE_ENGLISH("language_english"),
    LANGUAGE_RUSSIAN("language_russian"),
    FORGOT_PASSWORD("forgot-password"),

    PAGE_HOME("page_home"),
    PAGE_SIGN_IN("page_sign_in"),
    PAGE_SIGN_UP("page_sign_up"),
    PAGE_CONTINUE_AS_GUEST("page_continue_as_guest"),
    PASSWORD_TEXT("password-text"),
    SIGN_IN_BUTTON("sign-in-button"),
    SIGN_UP_BUTTON("sign-up-button"),
    SIGN_OUT_BUTTON("sign-out-button"),
    NAME_TEXT("name-text"),
    SURNAME_TEXT("surname-text"),
    EMAIL_TEXT("email-text"),
    CONFIRM_PASSWORD_TEXT("confirm-password-text"),
    SHOP("shop-button"),
    WELCOME_TEXT("welcome-text"),
    CURRENT_PASSWORD_TEXT("current-password-text"),
    NEW_PASSWORD_TEXT("new-password-text"),
    CONFIRM_NEW_PASSWORD_TEXT("confirm-new-password-text"),
    ACCOUNT_SETTINGS_TEXT("account-settings-text"),
    SAVE_CHANGES_TEXT("save-changes-text"),
    CANCEL_TEXT("cancel-text"),
    SETTINGS_BUTTON("settings-button"),
    GENERAL_SETTINGS_FIELD_TEXT("general-settings-field-text"),
    PASSWORD_SETTINGS_FIELD_TEXT("password-settings-field-text"),
    ADD_TO_CART_TEXT("add-to-cart-text"),
    OPEN_PRODUCT_PAGE_TEXT("open-product-page-text"),
    PRODUCT_NAME_FIELD("product-name-field"),
    PRODUCT_PRICE_FIELD("product-price-field"),
    CART_TEXT("cart-text"),
    DELETE_FROM_CART_TEXT("delete-from-cart-text"),
    SUCCESSFUL_MESSAGE_TEXT("successful-message-text"),
    ERROR_MESSAGE_TEXT("error-message-text"),
    DESCRIPTION_LABEL_TEXT("description-label-text"),
    ADDRESS_LABEL_TEXT("address-label-text"),
    DATE_LABEL_TEXT("date-label-text"),
    FULL_NAME_LABEL_TEXT("full-name-label-text"),
    ID_TEXT("id-text"),
    BALANCE_TEXT("balance-text"),
    CHANGE_USER_BUTTON_TEXT("change-user-button-text"),
    CHANGE_PRODUCT_BUTTON_TEXT("change-product-button-text"),
    CHANGE_ORDER_BUTTON_TEXT("change-order-button-text"),
    ROLE_TEXT("role-text"),
    IS_IN_STOCK_LABEL_TEXT("is-in-stock-label-text"),
    STATUS_TEXT("status-text"),
    USER_LABEL_TEXT("user-label-text"),
    ORDER_LABEL_TEXT("order-label-text"),
    ORDER_ID_TEXT("order-id-text"),
    ORDER_ORDER_ID_TEXT("order-order-id-text"),
    PRODUCT_NAME_TEXT("product-name-text"),
    ORDER_STATUS_TEXT("order-status-text"),
    ORDER_DETAILS_TEXT("order-details-text"),
    ORDER_DATE_TEXT("order-date-text"),
    ORDER_CREATOR_TEXT("order-creator-text"),
    ORDER_RECIPIENT_TEXT("order-recipient-text"),
    PRODUCT_SETTINGS_LABEL_TEXT("product-settings-label-text"),
    PRODUCT_GENERAL_SETTINGS_TEXT("product-general-settings-text"),
    PRODUCT_DESCRIPTION_LABEL_TEXT("product-description-label-text"),
    USER_ROLE_LABEL_TEXT("user-role-label-text"),
    USER_STATUS_LABEL_TEXT("user-status-label-text");



    private String value;

    LocaleKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
