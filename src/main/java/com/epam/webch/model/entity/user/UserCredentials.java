package com.epam.webch.model.entity.user;

import java.util.Objects;

public class UserCredentials {
    private String hashPasswordHex;
    private String saltHex;

    public UserCredentials(String hashPasswordHex, String saltHex) {
        this.hashPasswordHex = hashPasswordHex;
        this.saltHex = saltHex;
    }

    public String getHashPasswordHex() {
        return hashPasswordHex;
    }

    public String getSaltHex() {
        return saltHex;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentials that = (UserCredentials) o;
        return hashPasswordHex.equals(that.hashPasswordHex) && saltHex.equals(that.saltHex);
    }

    @Override
    public int hashCode() {
        int result=0;
        int multiplier=31;
        result+=multiplier*hashPasswordHex.hashCode();
        result=result*multiplier+saltHex.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result=new StringBuilder("UserCredentials{hashPasswordHex=");
        result.append(hashPasswordHex).append( ", saltHex=").append(saltHex).append("}");
        return result.toString();
    }
}
