package com.epam.webch.model.localization;

import java.util.Locale;

public enum LocaleAttribute {
    RU("Russian", new Locale("ru", "RU")),
    EN("English", new Locale("en", "GB"));

    private String value;
    private Locale locale;

    LocaleAttribute(String value, Locale locale) {
        this.value = value;
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }


    public static LocaleAttribute defineLocale(String localeFromString) {
        LocaleAttribute returnValue = EN;
        for (LocaleAttribute localeAttribute : values()) {
            if (localeAttribute.value.equals(localeFromString)) {
                returnValue = localeAttribute;
                break;
            }
        }
        return returnValue;
    }

    public String getValue() {
        return value;
    }
}
