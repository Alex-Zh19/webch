package com.epam.webch.model.util;

public class DataValidator {
    private static final String REG_TO_VALIDATE_EMAIL = "^\\w{2,}[@]\\w{2,8}[\\.]?(\\w{2,6})?$";
    private static final String REG_TO_VALIDATE_NAME_AND_SURNAME = "^\\w{2,28}$";
    private static final String REG_TO_VALIDATE_PASSWORD="^\\w{4,28}$";

    private DataValidator(){
    }

    public static boolean checkEmailOnCorrectness(String email){
        if (email.matches(REG_TO_VALIDATE_EMAIL)) {
            return true;
        }
        return false;
    }

    public static boolean validateName(String name){
        return name.matches(REG_TO_VALIDATE_NAME_AND_SURNAME);
    }

    public static boolean validateSurname(String surname) {
        return surname.matches(REG_TO_VALIDATE_NAME_AND_SURNAME);
    }

    public static boolean validatePassword(String password){
        return password.matches(REG_TO_VALIDATE_PASSWORD);
    }
}
