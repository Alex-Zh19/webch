package com.epam.webch.model.entity.order;

import com.epam.webch.controller.BaseEnum;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;

import java.sql.Date;

public class Order {
    private long id;
    private long orderId;
    private Product productName;
    private OrderStatus status;
    private OrderDetails orderDetails;
    private User creator;
    private User recipient;

   public class OrderDetails {
        private long id;
        private long orderId;
        private String details;
        private Date orderDate;

        OrderDetails(long id, long orderId, String details, Date orderDate) {
            this.id = id;
            this.orderId = orderId;
            this.details = details;
            this.orderDate = orderDate;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long orderId() {
            return Order.this.orderId;
        }

        public void orderId(long orderId) {
            this.orderId = orderId;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public Date getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderDetails that = (OrderDetails) o;
            return id == that.id && orderId == that.orderId && details.equals(that.details) &&
                    orderDate.equals(that.orderDate);
        }

        @Override
        public int hashCode() {
            int result = 0;
            int multiplier = 31;
            Long longId = id;
            Long longOrderId = orderId;
            result = result + longId.hashCode() * multiplier;
            result = multiplier * result + longOrderId.hashCode();
            result = multiplier * result + details.hashCode();
            result = multiplier * result + orderDate.hashCode();
            return result;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("OrderDetails{id=");
            result.append(id);
            result.append(", orderId=");
            result.append(orderId);
            result.append(", details=");
            result.append(details);
            result.append(", orderDate=");
            result.append(orderDate);
            result.append("}");
            return result.toString();
        }
   }

    public enum OrderStatus implements BaseEnum {
        processed("processed"),
        preparing("preparing"),
        ready("ready"),
        deleted("deleted");

        private String value;

        OrderStatus(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    public Order(long id, long orderId, Product productName,
                 OrderStatus status, long detailsId, long detailsOrderId,
                 String details, Date orderDate, User creator, User recipient) {
        this.id = id;
        this.orderId = orderId;
        this.productName = productName;
        this.status = status;
        this.orderDetails = new OrderDetails(detailsId, detailsOrderId, details, orderDate);
        this.creator = creator;
        this.recipient = recipient;
    }


    public Order(long id, long orderId,Product productName,
                 OrderStatus status, long detailsId, long detailsOrderId,
                 String details, Date orderDate, User creator) {
        this.id = id;
        this.orderId = orderId;
        this.productName = productName;
        this.status = status;
        this.orderDetails = new OrderDetails(detailsId, detailsOrderId, details, orderDate);
        this.creator = creator;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails details) {
        this.orderDetails = details;
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
                && status == order.status && orderDetails.equals(order.orderDetails)
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
        result = multiplier * result + orderDetails.hashCode();
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
        result.append(", orderDetails=");
        result.append(orderDetails);
        result.append(", creator=");
        result.append(creator);
        result.append(", recipient=");
        result.append(recipient);
        result.append("}");
        return result.toString();
    }
}
