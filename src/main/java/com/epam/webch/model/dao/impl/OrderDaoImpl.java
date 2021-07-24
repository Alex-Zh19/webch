package com.epam.webch.model.dao.impl;

import com.epam.webch.exception.DaoException;
import com.epam.webch.model.connection.ConnectionPool;
import com.epam.webch.model.connection.ProxyConnection;
import com.epam.webch.model.dao.OrderDao;
import com.epam.webch.model.dao.query.OrderQueryFactory;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static final OrderDao instance = new OrderDaoImpl();
    private static final Logger logger = LogManager.getLogger();

    //table columns start
    private static final String ID_COLUMN = "id";
    private static final String PRODUCT_NAME_COLUMN = "productName";
    private static final String STATUS_COLUMN = "status";
    private static final String DETAILS_COLUMN = "details";
    private static final String CREATOR_COLUMN = "creator";
    private static final String RECIPIENT_COLUMN = "recipient";
    //table columns end

    private OrderDaoImpl(){

    }

    public static OrderDao getInstance(){
        return instance;
    }

    @Override
    public List<Optional<Order>> findAllOrders() throws DaoException {
        List<Optional<Order>> orders = new ArrayList<>();
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.findAllOrdersQuery();
        Optional<Order> order;
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    long id = resultSet.getLong(ID_COLUMN);
                    Product productName = (Product) resultSet.getObject(PRODUCT_NAME_COLUMN);
                    String statusString = resultSet.getString(STATUS_COLUMN);
                    Order.OrderStatus status = Order.OrderStatus.valueOf(statusString);
                    Optional<String> details = Optional.of(resultSet.getString(DETAILS_COLUMN));
                    User orderCreator = (User) resultSet.getObject(CREATOR_COLUMN);
                    Optional<User> orderRecipient = Optional.of((User) resultSet.getObject(RECIPIENT_COLUMN));
                    if (details.isPresent() && orderRecipient.isPresent()) {
                        order = Optional.of(new Order(id, productName, status, details.get(), orderCreator, orderRecipient.get()));
                    } else if (details.isPresent()) {
                        order = Optional.of(new Order(id, productName, status, details.get(), orderCreator));
                    } else if (orderRecipient.isPresent()) {
                        order = Optional.of(new Order(id, productName, status, orderCreator, orderRecipient.get()));
                    } else {
                        order = Optional.of(new Order(id, productName, status, orderCreator));
                    }
                    orders.add(order);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "getAllOrders SqlException {}", e);
                throw new DaoException("getAllOrders SqlException " + e);
            }
        }
        return orders;
    }

    @Override
    public List<Optional<Order>> findOrdersByCreator(User creator) throws DaoException {
        List<Optional<Order>> orders = new ArrayList<>();
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.findOrdersByCreatorQuery(creator);
        Optional<Order> order;
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    long id = resultSet.getLong(ID_COLUMN);
                    Product productName = (Product) resultSet.getObject(PRODUCT_NAME_COLUMN);
                    String statusString = resultSet.getString(STATUS_COLUMN);
                    Order.OrderStatus status = Order.OrderStatus.valueOf(statusString);
                    Optional<String> details = Optional.of(resultSet.getString(DETAILS_COLUMN));
                    User orderCreator = (User) resultSet.getObject(CREATOR_COLUMN);
                    Optional<User> orderRecipient = Optional.of((User) resultSet.getObject(RECIPIENT_COLUMN));
                    if (details.isPresent() && orderRecipient.isPresent()) {
                        order = Optional.of(new Order(id, productName, status, details.get(), orderCreator, orderRecipient.get()));
                    } else if (details.isPresent()) {
                        order = Optional.of(new Order(id, productName, status, details.get(), orderCreator));
                    } else if (orderRecipient.isPresent()) {
                        order = Optional.of(new Order(id, productName, status, orderCreator, orderRecipient.get()));
                    } else {
                        order = Optional.of(new Order(id, productName, status, orderCreator));
                    }
                    orders.add(order);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "getOrdersByCreator SqlException {}", e);
                throw new DaoException("getOrdersByCreator SqlException " + e);
            }
        }
        return orders;
    }

    @Override
    public Optional<Order> findOrder(Long idInBase) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.findOrderQuery(idInBase);
        Optional<Order> order = Optional.empty();
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    long id = resultSet.getLong(ID_COLUMN);
                    Product productName = (Product) resultSet.getObject(PRODUCT_NAME_COLUMN);
                    String statusString = resultSet.getString(STATUS_COLUMN);
                    Order.OrderStatus status = Order.OrderStatus.valueOf(statusString);
                    Optional<String> details = Optional.of(resultSet.getString(DETAILS_COLUMN));
                    User orderCreator = (User) resultSet.getObject(CREATOR_COLUMN);
                    Optional<User> orderRecipient = Optional.of((User) resultSet.getObject(RECIPIENT_COLUMN));
                    if (details.isPresent() && orderRecipient.isPresent()) {
                        order = Optional.of(new Order(id, productName, status, details.get(), orderCreator, orderRecipient.get()));
                    } else if (details.isPresent()) {
                        order = Optional.of(new Order(id, productName, status, details.get(), orderCreator));
                    } else if (orderRecipient.isPresent()) {
                        order = Optional.of(new Order(id, productName, status, orderCreator, orderRecipient.get()));
                    } else {
                        order = Optional.of(new Order(id, productName, status, orderCreator));
                    }
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "getOrderById SqlException {}", e);
                throw new DaoException("getOrderById SqlException " + e);
            }
        }
        return order;
    }

    @Override
    public void addOrder(Product product, Order.OrderStatus status, String details, User creator, User recipient)
            throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.addOrderQuery(product, status, details, creator, recipient);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "createOrder SqlException {}", e);
                throw new DaoException("createOrder SqlException " + e);
            }
        }
    }

    @Override
    public void deleteOrder(Order order) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.deleteOrderQuery(order);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "deleteOrder SqlException {}", e);
                throw new DaoException("deleteOrder SqlException " + e);
            }
        }
    }

    @Override
    public void reallyDeleteOrder(Order order) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.deleteOrderQuery(order);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "deleteOrder SqlException {}", e);
                throw new DaoException("deleteOrder SqlException " + e);
            }
        }
    }

    @Override
    public void addOrder(Product product, Order.OrderStatus status, User creator, User recipient) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.addOrderQuery(product, status, creator, recipient);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "createOrder SqlException {}", e);
                throw new DaoException("createOrder SqlException " + e);
            }
        }
    }

    @Override
    public void addOrder(Product product, Order.OrderStatus status, String details, User creator) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.addOrderQuery(product, status, details, creator);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "createOrder SqlException {}", e);
                throw new DaoException("createOrder SqlException " + e);
            }
        }
    }

    @Override
    public void addOrder(Product product, Order.OrderStatus status, User creator) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.addOrderQuery(product, status, creator);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "createOrder SqlException {}", e);
                throw new DaoException("createOrder SqlException " + e);
            }
        }
    }

    @Override
    public void changeOrderStatus(Long id, Order.OrderStatus status) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.changeOrderStatusQuery(id, status);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "changeOrderStatus SqlException {}", e);
                throw new DaoException("changeOrderStatus SqlException " + e);
            }
        }
    }
}
