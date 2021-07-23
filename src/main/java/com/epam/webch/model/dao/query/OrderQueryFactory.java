package com.epam.webch.model.dao.query;

import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;

import static com.epam.webch.model.dao.query.SQLQuery.*;

public class OrderQueryFactory {

    private static final String ORDERS = "orders ";
    private static final String ALL_FIELDS_INSERT = "(productName,status,details,creator,recipient) ";
    private static final String INSERT_WITHOUT_DETAILS = "(productName,status,creator,recipient) ";
    private static final String INSERT_WITHOUT_RECIPIENT = "(productName,status,details,creator) ";
    private static final String INSERT_WITHOUT_BOTH = "(productName,status,creator) ";
    private static final String ALL_FIELDS_FIND = "id,productName,status,details,creator,recipient ";

    public  String findAllOrdersQuery() {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND).append(FROM).append(ORDERS);
        return query.toString();
    }


    public  String addOrderQuery(Product product, Order.OrderStatus status, String details, User creator, User recipient) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(ORDERS).append(ALL_FIELDS_INSERT);
        query.append("values(\"");
        query.append(product.getId()).append("\"").append(",");
        query.append("\"").append(status).append("\"").append(",");
        query.append("\"").append(details).append("\"").append(",");
        query.append("\"").append(creator).append("\"").append(",");
        query.append("\"").append(recipient).append("\"").append(")");
        return query.toString();
    }

    public  String addOrderQuery(Product product, Order.OrderStatus status, User creator, User recipient) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(ORDERS).append(INSERT_WITHOUT_DETAILS);
        query.append("values(\"");
        query.append(product.getId()).append("\"").append(",");
        query.append("\"").append(status).append("\"").append(",");
        query.append("\"").append(creator).append("\"").append(",");
        query.append("\"").append(recipient).append("\"").append(")");
        return query.toString();
    }
    public  String addOrderQuery(Product product, Order.OrderStatus status, String details, User creator) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(ORDERS).append(INSERT_WITHOUT_RECIPIENT);
        query.append("values(\"");
        query.append(product.getId()).append("\"").append(",");
        query.append("\"").append(status).append("\"").append(",");
        query.append("\"").append(details).append("\"").append(",");
        query.append("\"").append(creator).append("\"").append(")");
        return query.toString();
    }
    public  String addOrderQuery(Product product, Order.OrderStatus status, User creator) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(ORDERS).append(INSERT_WITHOUT_BOTH);
        query.append("values(\"");
        query.append(product.getId()).append("\"").append(",");
        query.append("\"").append(status).append("\"").append(",");
        query.append("\"").append(creator).append("\"").append(")");
        return query.toString();
    }

    public  String deleteOrderQuery(Order order) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(ORDERS).append(SET);
        query.append("status=").append("\"").append("deleted").append("\"");
        query.append(WHERE).append("id=").append(order.getId());
        return query.toString();
    }


    public  String reallyDeleteOrderQuery(Order order) {
        StringBuilder query = new StringBuilder(DELETE);
        query.append(FROM).append(ORDERS).append(WHERE).append("id=").
                append("\"").append(order.getId()).append("\"");
        return query.toString();
    }

    public  String selectCommandQuery(Order order) {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND);
        query.append(FROM).append(ORDERS).append(WHERE).append("id=").
                append("\"").append(order.getId()).append("\"");
        return query.toString();
    }

    public  String findOrdersByCreatorQuery(User creator) {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND);
        query.append(FROM).append(ORDERS).append(WHERE).append("creator=").
                append("\"").append(creator.getId()).append("\"");
        return query.toString();
    }

    public  String findOrderQuery(Long id) {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND);
        query.append(FROM).append(ORDERS).append(WHERE).append("id=").
                append("\"").append(id).append("\"");
        return query.toString();
    }

    public  String changeOrderStatusQuery(Long id, Order.OrderStatus status) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(ORDERS).append(SET);
        query.append("status=").append("\"").append(status).append("\"");
        query.append(WHERE).append("id=").append(id);
        return query.toString();
    }
}
