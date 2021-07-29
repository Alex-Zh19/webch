package com.epam.webch.model.dao.query;

import com.epam.webch.model.entity.user.User;

import static com.epam.webch.model.dao.query.SQLQuery.*;

public class UserQueryFactory {
    private static final String USERS = "users ";
    private static final String ALL_FIELDS_INSERT = "(email,password,salt,name,surname,balance,role,status) ";
    private static final String FIELDS_WITHOUT_BALANCE_ROLE_STATUS_INSERT = "(email,password,salt,name,surname) ";
    private static final String FIELDS_WITHOUT_BALANCE_ROLE_STATUS_SURNAME_INSERT = "(email,password,salt,name) ";
    private static final String ALL_FIELDS_FIND = "id,email,name,surname,balance,role,status ";

    public String findAllQuery() {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND).append(FROM).append(USERS);
        return query.toString();
    }

    public String findAllUsersQuery() {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND).append(FROM).append(USERS).append(WHERE).append("role=").append("\"user\"");
        return query.toString();
    }

    public String findAllEmployeesQuery() {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND).append(FROM).append(USERS).append(WHERE).append("role=").append("\"employee\"");;
        return query.toString();
    }

    public String findAllAdminsQuery() {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND).append(FROM).append(USERS).append(WHERE).append("role=").append("\"admin\"");;
        return query.toString();
    }

    public String createUserQuery(String email, String hashPassword, String salt, String name,
                                  String surname, int balance, User.UserRole role, User.UserStatus status) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(USERS);
        query.append(ALL_FIELDS_INSERT).append("values(");
        query.append("\"").append(email).append("\"").append(",");
        query.append("\"").append(hashPassword).append("\"").append(",");
        query.append("\"").append(salt).append("\"").append(",");
        query.append("\"").append(name).append("\"").append(",");
        query.append("\"").append(surname).append("\"").append(",");
        query.append("\"").append(balance).append("\"").append(",");
        query.append("\"").append(role).append("\"").append(",");
        query.append("\"").append(status).append("\"").append(")");
        return query.toString();
    }
    public String createUserQuery(String email, String hashPassword, String salt, String name,
                                  int balance, User.UserRole role, User.UserStatus status) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(USERS).append(ALL_FIELDS_INSERT).append("values(");

        query.append("\"").append(email).append("\"").append(",");
        query.append("\"").append(hashPassword).append("\"").append(",");
        query.append("\"").append(salt).append("\"").append(",");
        query.append("\"").append(name).append("\"").append(",");
        query.append("\"").append(balance).append("\"").append(",");
        query.append("\"").append(role).append("\"").append(",");
        query.append("\"").append(status).append("\"").append(")");
        return query.toString();
    }

    public String createUserQuery(String email, String hashPassword, String salt, String name) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(USERS).append(FIELDS_WITHOUT_BALANCE_ROLE_STATUS_SURNAME_INSERT).append("values(");

        query.append("\"").append(email).append("\"").append(",");
        query.append("\"").append(hashPassword).append("\"").append(",");
        query.append("\"").append(salt).append("\"").append(",");
        query.append("\"").append(name).append("\"").append(")");
        return query.toString();
    }

    public String createUserQuery(String email, String hashPassword, String salt, String name,String surname) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(USERS).append(FIELDS_WITHOUT_BALANCE_ROLE_STATUS_INSERT).append("values(");

        query.append("\"").append(email).append("\"").append(",");
        query.append("\"").append(hashPassword).append("\"").append(",");
        query.append("\"").append(salt).append("\"").append(",");
        query.append("\"").append(name).append("\"").append(",");
        query.append("\"").append(surname).append("\"").append(")");
        return query.toString();
    }

    public String deleteUserQuery(Long id) {//
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(USERS).append(SET);
        query.append("status=").append("\"").append("deleted").append("\" ");
        query.append(WHERE).append("id=").append("\"").append(id).append("\"");
        return query.toString();

    }

    public String activateUserQuery(String email){
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(USERS).append(SET);
        query.append("status=").append("\"").append("active").append("\" ");
        query.append(WHERE).append("email=").append("\"").append(email).append("\"");
        return query.toString();
    }

    public String reallyDeleteUserQuery(User user) {//
        StringBuilder query = new StringBuilder(DELETE);
        query.append(FROM).append(USERS);
        query.append(WHERE).append("id=").append("\"").append(user.getId()).append("\"");
        return query.toString();

    }

    public String findUserByEmailQuery(String email) {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND);
        query.append(FROM).append(USERS).append(WHERE).append("email=").
                append("\"").append(email).append("\"");
        return query.toString();
    }

    public String findUserByIdQuery(Long id) {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND);
        query.append(FROM).append(USERS).append(WHERE).append("id=").
                append("\"").append(id).append("\"");
        return query.toString();
    }

    public String findUserCredentialsQuery(String email){
        StringBuilder query=new StringBuilder("select password,salt from users where email=");
        query.append("\"").append(email).append("\"");
        return query.toString();
    }

    public String updateCommandQuery(Long id, String email, String hashPassword, String salt, String name,
                                     String surname, int balance, User.UserRole role, User.UserStatus status) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(USERS).append(SET);
        query.append("email=").append("\"").append(email).append("\"").append(",");
        query.append("password=").append("\"").append(hashPassword).append("\"").append(",");
        query.append("salt=").append("\"").append(salt).append("\"").append(",");
        query.append("name=").append("\"").append(name).append("\"").append(",");
        query.append("surname=").append("\"").append(surname).append("\"").append(",");
        query.append("balance=").append("\"").append(balance).append("\"").append(",");
        query.append("role=").append("\"").append(role).append("\"").append(",");
        query.append("status=").append("\"").append(status).append("\" ");
        query.append(WHERE).append("id=").append("\"").append(id).append("\"");
        return query.toString();
    }

    public String changeUserStatusQuery(String email, User.UserStatus status) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(USERS).append(SET);
        query.append("status=").append("\"").append(status).append("\" ");
        query.append(WHERE).append("email=").append("\"").append(email).append("\" ");
        return query.toString();
    }

    public String changeUserRoleQuery(String email, User.UserRole role) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(USERS).append(SET);
        query.append("role=").append("\"").append(role).append("\" ");
        query.append(WHERE).append("email=").append("\"").append(email).append("\" ");
        return query.toString();
    }

    public String changeUserNameQuery(String email, String name) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(USERS).append(SET);
        query.append("name=").append("\"").append(name).append("\" ");
        query.append(WHERE).append("email=").append("\"").append(email).append("\" ");
        return query.toString();
    }

    public String changeUserEmailQuery(String email, String newEmail) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(USERS).append(SET);
        query.append("email=").append("\"").append(newEmail).append("\" ");
        query.append(WHERE).append("email=").append("\"").append(email).append("\" ");
        return query.toString();
    }

    public String changeUserSurnameQuery(String email, String surname) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(USERS).append(SET);
        query.append("surname=").append("\"").append(surname).append("\" ");
        query.append(WHERE).append("email=").append("\"").append(email).append("\" ");
        return query.toString();
    }

    public String changeUserPasswordQuery(String email, String password,String salt) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(USERS).append(SET);
        query.append("password=").append("\"").append(password).append("\"").append(",");
        query.append("salt=").append("\"").append(salt).append("\" ");
        query.append(WHERE).append("email=").append("\"").append(email).append("\" ");
        return query.toString();
    }

}
