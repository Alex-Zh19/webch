package com.epam.webch.model.dao.query;

import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.user.User;

import java.sql.Date;

import static com.epam.webch.model.dao.query.SQLQuery.*;

public class OrderQueryFactory {
//maybe cross join
    private static final String ORDERS = "coffee_house.orders ";
    private static final String ORDERS_DETAILS = "coffee_house.orders_details ";
    private static final String PRODUCTS = "coffee_house.products ";
    private static final String ID_FIELD_ORDERS_DETAILS_FIND="orders_details.id ";
    private static final String ORDER_ID_FIELD_ORDERS_DETAILS_FIND="orders_details.order_id";
    private static final String ALL_FIELDS_ORDERS_DETAILS_INSERT = "(order_id,details,order_date) ";
    private static final String ALL_FIELDS_ORDERS_INSERT = "(order_id,productName,status,details,creator) ";
    private static final String INSERT_WITHOUT_CREATOR = "(order_id,productName,status,details) ";
    private static final String ALL_FIELDS_ORDERS_FIND =
            "orders.id,orders.status,orders.creator,orders.recipient,orders_details.id," +
                    "orders_details.details,orders_details.order_date,products.id,products.name,products.price," +
                    "products.description,products.inStock ";

    public String findAllOrdersQuery() {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_ORDERS_FIND)
                .append(FROM).append(ORDERS).append(JOIN).append(ORDERS_DETAILS).append(ON).
                append("coffee_house.orders.order_id=coffee_house.orders_details.order_id ").append(JOIN).
                append(PRODUCTS).append(ON).append("coffee_house.orders.productName=coffee_house.products.id ");
        return query.toString();
    }

    public String addOrderDetailsQuery(long order_id, String details, Date order_date){
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(ORDERS_DETAILS).append(ALL_FIELDS_ORDERS_DETAILS_INSERT);
        query.append("values(");
        query.append(order_id).append(",");
        query.append("\"").append(details).append("\"").append(",");
        query.append("\"").append(order_date).append("\"").append(")");
        System.out.println(query);
        return query.toString();
    }

    public String findLastOrderId(){
        StringBuilder query=new StringBuilder(SELECT);
        query.append(MAX).append("(").append(ORDER_ID_FIELD_ORDERS_DETAILS_FIND).append(") ").append(AS).append("id ").
                append(FROM).append(ORDERS_DETAILS);
        System.out.println(query);
        return query.toString();
    }


    public  String findOrdersDetailsQuery(long details_Id) {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ID_FIELD_ORDERS_DETAILS_FIND)
                .append(FROM).append(ORDERS_DETAILS).append(WHERE).append("order_id=").append(details_Id);
        System.out.println(query);
        return query.toString();
    }

    public String addOrderQuery(long order_id,long prodId, Order.OrderStatus status, long details) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(ORDERS).append(INSERT_WITHOUT_CREATOR);
        query.append("values(");
        query.append(order_id).append(",").append(prodId).append(",");
        query.append("\"").append(status).append("\"").append(",");
        query.append(details).append(")");
        System.out.println(query);
        return query.toString();
    }

    public String addOrderQuery(long order_id,long prodId, Order.OrderStatus status, long details, User creator) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(ORDERS).append(ALL_FIELDS_ORDERS_INSERT);
        query.append("values(");
        query.append(order_id).append(",").append(prodId).append(",");
        query.append("\"").append(status).append("\"").append(",");
        query.append(details).append(",");
        query.append("\"").append(creator).append("\"").append(")");
        System.out.println(query);
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
        query.append(FROM).append(ORDERS).append(WHERE).append("id=").append(order.getId());
        return query.toString();
    }

    public  String selectCommandQuery(Order order) {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_ORDERS_FIND)
                .append(FROM).append(ORDERS).append(JOIN).append(ORDERS_DETAILS).append(ON).
                append("coffee_house.orders.order_id=coffee_house.orders_details.order_id").append(JOIN).
                append(PRODUCTS).append(ON).append("coffee_house.orders.productName=coffee_house.products.id").
                append(WHERE).append("orders.order_id=").append(order.getId());
        return query.toString();
    }

    public  String findOrdersByCreatorQuery(User creator) {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_ORDERS_FIND)
                .append(FROM).append(ORDERS).append(JOIN).append(ORDERS_DETAILS).append(ON).
                append("coffee_house.orders.order_id=coffee_house.orders_details.order_id").append(JOIN).
                append(PRODUCTS).append(ON).append("coffee_house.orders.productName=coffee_house.products.id").
                append(WHERE).append("orders.creator=").append(creator.getId());
        return query.toString();
    }

    public  String findOrderQuery(Long id) {
        StringBuilder query = new StringBuilder(SELECT);
        query.append(ALL_FIELDS_ORDERS_FIND)
                .append(FROM).append(ORDERS).append(JOIN).append(ORDERS_DETAILS).append(ON).
                append("coffee_house.orders.order_id=coffee_house.orders_details.order_id").append(JOIN).
                append(PRODUCTS).append(ON).append("coffee_house.orders.productName=coffee_house.products.id").
                append(WHERE).append("orders.order_id=").append(id);
        return query.toString();
    }

    public  String changeOrderStatusQuery(Long id, Order.OrderStatus status) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(ORDERS).append(SET);
        query.append("status=").append("\"").append(status).append("\"");
        query.append(WHERE).append("order_id=").append(id);
        return query.toString();
    }
    public  String changeOrderRecipientQuery(Long id, Long userId) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(ORDERS).append(SET);
        query.append("recipient=").append("\"").append(userId).append("\"");
        query.append(WHERE).append("order_id=").append(id);
        return query.toString();
    }
}
