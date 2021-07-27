package com.epam.webch.model.entity.user;

import com.epam.webch.controller.BaseEnum;

public class User {
    private long id;
    private String email;
    private String name;
    private String surname;
    private int balance;
    private UserRole userRole;
    private UserStatus userStatus;

    public enum UserRole implements BaseEnum {
        root("root"),
        admin("admin"),
        employee("employee"),
        user("user"),
        guest("guest");

        private String value;
        UserRole(String value){
            this.value=value;
        }
        @Override
        public String getValue() {
            return value;
        }
    }

    public enum UserStatus implements BaseEnum{
        active("active"),
        blocked("blocked"),
        deleted("deleted");

        private String value;
        UserStatus(String value){
            this.value=value;
        }
        @Override
        public String getValue() {
            return value;
        }
    }


    public User(long id, String email, String name,
                UserRole userRole, UserStatus userStatus) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public User(String email, String name, String surname) {
        this.email=email;
        this.name = name;
        this.surname=surname;
        userRole= UserRole.user;
        userStatus= UserStatus.active;
    }

    public User(long id, String email, String name,
                String surname, UserRole userRole, UserStatus userStatus) {
        this.id = id;
        this.email=email;
        this.name = name;
        this.surname = surname;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public User( UserRole userRole) {
        this.userRole = userRole;
    }

    public User(long id, String email, String name,
                int balance, UserRole userRole, UserStatus userStatus) {
        this.id = id;
        this.email=email;
        this.name = name;
        this.balance=balance;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }


    public User(long id, String email, String name,
                String surname, int balance, UserRole userRole, UserStatus userStatus) {
        this.id = id;
        this.email=email;
        this.name = name;
        this.surname = surname;
        this.balance=balance;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }






    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id == that.id &&  name.equals(that.name)
                && surname.equals( that.surname) && userRole == that.userRole &&
                userStatus == that.userStatus;
    }


    @Override
    public int hashCode() {
        int multiplier = 31;
        int result = 0;
        result += id * multiplier;
        result = multiplier * result + name.hashCode();
        result = multiplier * result + surname.hashCode();
        result = multiplier * result + userRole.hashCode();
        result = multiplier * result + userStatus.hashCode();
        return result;
    }
    @Override
    public String toString() {
        StringBuilder result=new StringBuilder("User{ id=");
        result.append(id);
        result.append(", name=");
        result.append(name);
        result.append(", surname=");
        result.append(surname);
        result.append(", userRole=");
        result.append(userRole);
        result.append(", userStatus=");
        result.append(userStatus);
        result.append("}");
        return result.toString();
    }
}
