package com.epam.webch.model.entity.order;

import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;

public class Order {
    long id;
    Product productName;
    OrderStatus status;
    String details;
    User creator;
    User recipient;


    public enum OrderStatus {
        processed,
        preparing,
        ready,
        deleted
    }

    public Order(long id, Product productName,
                 OrderStatus status, String details, User creator, User recipient) {
        this.id = id;
        this.productName = productName;
        this.status = status;
        this.details = details;
        this.creator = creator;
        this.recipient = recipient;
    }

    public Order(long id, Product productName,
                 OrderStatus status, User creator, User recipient) {
        this.id = id;
        this.productName = productName;
        this.status = status;
        this.creator = creator;
        this.recipient = recipient;
    }
    public Order(long id, Product productName,
                 OrderStatus status,String details, User creator) {
        this.id = id;
        this.productName = productName;
        this.status = status;
        this.details=details;
        this.creator = creator;
    }

    public Order(long id, Product productName,
                 OrderStatus status, User creator) {
        this.id = id;
        this.productName = productName;
        this.status = status;
        this.creator = creator;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProductName() {
        return productName;
    }

    public void setProductName(Product productName) {
        this.productName = productName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && productName.equals(order.productName)
                && status == order.status && details.equals(order.details)
                && creator.equals(order.creator)
                && recipient.equals(order.recipient);
    }

    @Override
    public int hashCode() {
        int multiplier = 31;
        int result = 0;
        Long idLong = id;
        result += multiplier * idLong.hashCode();
        result = multiplier * result + productName.hashCode();
        result = multiplier * result + status.hashCode();
        result = multiplier * result + details.hashCode();
        result = multiplier * result + creator.hashCode();
        result = multiplier * result + recipient.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Order{id=");
        result.append(id);
        result.append(", productName=");
        result.append(productName);
        result.append(", status=");
        result.append(status);
        result.append(", details=");
        result.append(details);
        result.append(", creator=");
        result.append(creator);
        result.append(", recipient=");
        result.append(recipient);
        result.append("}");
        return result.toString();
    }
}
