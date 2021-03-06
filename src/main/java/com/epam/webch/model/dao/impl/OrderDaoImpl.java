package com.epam.webch.model.dao.impl;

import com.epam.webch.exception.DaoException;
import com.epam.webch.model.connection.ConnectionPool;
import com.epam.webch.model.connection.ProxyConnection;
import com.epam.webch.model.dao.OrderDao;
import com.epam.webch.model.dao.UserDao;
import com.epam.webch.model.dao.query.OrderQueryFactory;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.entity.user.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static final OrderDao instance = new OrderDaoImpl();
    private static final Logger logger = LogManager.getLogger();

    //table orders columns start
    private static final String ID_COLUMN = "id";
    private static final String ORDER_ID_COLUMN = "order_id";
    private static final String PRODUCT_NAME_COLUMN = "productName";
    private static final String STATUS_COLUMN = "status";
    private static final String DETAILS_COLUMN = "details";
    private static final String CREATOR_COLUMN = "creator";
    private static final String RECIPIENT_COLUMN = "recipient";
    //table orders columns end

    //table orders details columns start
    private static final String ID_DETAILS_COLUMN = "id";
    private static final String DATE_ORDER_COLUMN = "order_date";
    private static final String DETAILS_ORDER_DETAILS_COLUMN = "details";
    //table orders details columns end

    //table products columns start
    private static final String ID_PRODUCT_COLUMN = "id";
    private static final String NAME_PRODUCT_COLUMN = "name";
    private static final String PRICE_PRODUCT_COLUMN = "price";
    private static final String DESCRIPTION_PRODUCT_COLUMN = "description";
    private static final String IN_STOCK_PRODUCT_COLUMN = "inStock";
    //table products columns end


    private OrderDaoImpl() {

    }

    public static OrderDao getInstance() {
        return instance;
    }

    @Override
    public List<Optional<Order>> findAllOrders() throws DaoException {
        List<Optional<Order>> orders = new ArrayList<>();
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.findAllOrdersQuery();
        System.out.println(query);
        Optional<Order> order;
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    long orderId = resultSet.getLong(ID_COLUMN);//auto-incremented
                    long order_id = resultSet.getLong(ORDER_ID_COLUMN);
                    //product
                    long productId = resultSet.getLong(ID_PRODUCT_COLUMN);
                    String productName = resultSet.getString(NAME_PRODUCT_COLUMN);
                    String productDescription=resultSet.getString(DESCRIPTION_PRODUCT_COLUMN);
                    int productPrice = resultSet.getInt(PRICE_PRODUCT_COLUMN);
                    int inStock = resultSet.getInt(IN_STOCK_PRODUCT_COLUMN);
                    //product
                    Product product = new Product(productId, productName, productPrice, productDescription, inStock);

                    String statusString = resultSet.getString(STATUS_COLUMN);
                    Order.OrderStatus status = Order.OrderStatus.valueOf(statusString);
                    //details
                    long detailsId = resultSet.getLong(ID_DETAILS_COLUMN);
                    long details_id = order_id;
                    String details = resultSet.getString(DETAILS_ORDER_DETAILS_COLUMN);
                    Date detailsDate = resultSet.getDate(DATE_ORDER_COLUMN);
                    //details
                    Long orderCreatorId = resultSet.getLong(CREATOR_COLUMN);
                    UserDao userDao = UserDaoImpl.getInstance();
                    Optional<User> orderCreator;
                    try {
                        orderCreator = userDao.findUserById(orderCreatorId);
                    } catch (DaoException e) {
                        logger.log(Level.ERROR, "findUserById at find all orders  DAOException {}", e);
                        throw new DaoException("findUserById at find all orders  DAOException " + e);
                    }
                    Optional<Long> orderRecipientId = Optional.ofNullable( resultSet.getLong(RECIPIENT_COLUMN));
                    Optional<User> orderRecipient=Optional.empty();
                    if (orderRecipientId.isPresent()) {
                        try {
                            orderRecipient = userDao.findUserById(orderCreatorId);
                        } catch (DaoException e) {
                            logger.log(Level.ERROR, "findUserById at find all orders  DAOException {}", e);
                            throw new DaoException("findUserById at find all orders  DAOException " + e);
                        }
                    }
                    if (!orderRecipient.isEmpty()) {
                        order = Optional.of(new Order(orderId, order_id, product, status, detailsId, details_id, details,
                                detailsDate, orderCreator.get(), orderRecipient.get()));
                    } else {
                        order = Optional.of(new Order(orderId, order_id, product, status, detailsId, details_id, details,
                                detailsDate, orderCreator.get()));
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
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    long orderId = resultSet.getLong(ID_COLUMN);//auto-incremented
                    long order_id = resultSet.getLong(ORDER_ID_COLUMN);
                    //product
                    long productId = resultSet.getLong(ID_PRODUCT_COLUMN);
                    String productName = resultSet.getString(NAME_PRODUCT_COLUMN);
                    int productPrice = resultSet.getInt(PRICE_PRODUCT_COLUMN);
                    String productDescription=resultSet.getString(DESCRIPTION_PRODUCT_COLUMN);
                    int inStock = resultSet.getInt(IN_STOCK_PRODUCT_COLUMN);
                    //product
                    Product product = new Product(productId, productName, productPrice,productDescription,inStock);

                    String statusString = resultSet.getString(STATUS_COLUMN);
                    Order.OrderStatus status = Order.OrderStatus.valueOf(statusString);
                    //details
                    long detailsId = resultSet.getLong(ID_DETAILS_COLUMN);
                    long details_id = order_id;
                    String details = resultSet.getString(DETAILS_ORDER_DETAILS_COLUMN);
                    Date detailsDate = resultSet.getDate(DATE_ORDER_COLUMN);
                    //details
                    Long orderCreatorId = resultSet.getLong(CREATOR_COLUMN);
                    UserDao userDao = UserDaoImpl.getInstance();
                    Optional<User> orderCreator;
                    try {
                        orderCreator = userDao.findUserById(orderCreatorId);
                    } catch (DaoException e) {
                        logger.log(Level.ERROR, "findUserById at findOrdersByCreator  DAOException {}", e);
                        throw new DaoException("findUserById at findOrdersByCreator  DAOException " + e);
                    }

                    Optional<Long> orderRecipientId = Optional.ofNullable(resultSet.getLong(RECIPIENT_COLUMN));
                    Optional<User> orderRecipient=Optional.empty();
                    if (orderRecipientId.isPresent()) {
                        try {
                            orderRecipient = userDao.findUserById(orderRecipientId.get());
                        } catch (DaoException e) {
                            logger.log(Level.ERROR, "findUserById at find all orders  DAOException {}", e);
                            throw new DaoException("findUserById at find all orders  DAOException " + e);
                        }
                    }
                    if (!orderRecipient.isEmpty()) {
                        order = Optional.of(new Order(orderId, order_id, product, status, detailsId, details_id, details,
                                detailsDate, orderCreator.get(), orderRecipient.get()));
                    } else {
                        order = Optional.of(new Order(orderId, order_id, product, status, detailsId, details_id, details,
                                detailsDate, orderCreator.get()));
                    }
                    orders.add(order);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "findOrdersByCreator SqlException {}", e);
                throw new DaoException("findOrdersByCreator SqlException " + e);
            }
        }
        return orders;
    }

    @Override
    public List<Optional<Order>> findOrder(Long order_id) throws DaoException {
        List<Optional<Order>> list=new ArrayList<>();
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.findOrderQuery(order_id);
        Optional<Order> order ;
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    long orderId = resultSet.getLong(ID_COLUMN);//auto-incremented
                    long idOrder = resultSet.getLong(ORDER_ID_COLUMN);
                    //product
                    long productId = resultSet.getLong(ID_PRODUCT_COLUMN);
                    String productName = resultSet.getString(NAME_PRODUCT_COLUMN);
                    int productPrice = resultSet.getInt(PRICE_PRODUCT_COLUMN);
                    String productDescription=resultSet.getString(DESCRIPTION_PRODUCT_COLUMN);
                    int inStock = resultSet.getInt(IN_STOCK_PRODUCT_COLUMN);
                    //product
                    Product product = new Product(productId, productName, productPrice, productDescription,inStock);

                    String statusString = resultSet.getString(STATUS_COLUMN);
                    Order.OrderStatus status = Order.OrderStatus.valueOf(statusString);
                    //details
                    long detailsId = resultSet.getLong(ID_DETAILS_COLUMN);
                    long details_id = idOrder;
                    String details = resultSet.getString(DETAILS_ORDER_DETAILS_COLUMN);
                    Date detailsDate = resultSet.getDate(DATE_ORDER_COLUMN);
                    //details
                    Long orderCreatorId = resultSet.getLong(CREATOR_COLUMN);
                    UserDao userDao = UserDaoImpl.getInstance();
                    Optional<User> orderCreator;
                    try {
                        orderCreator = userDao.findUserById(orderCreatorId);
                    } catch (DaoException e) {
                        logger.log(Level.ERROR, "findUserById at findOrder  DAOException {}", e);
                        throw new DaoException("findUserById at findOrder  DAOException " + e);
                    }

                    Optional<Long> orderRecipientId = Optional.ofNullable( resultSet.getLong(RECIPIENT_COLUMN));
                    Optional<User> orderRecipient=Optional.empty();
                    if (orderRecipientId.isPresent()) {
                        try {
                            orderRecipient = userDao.findUserById(orderCreatorId);
                        } catch (DaoException e) {
                            logger.log(Level.ERROR, "findUserById at find all orders  DAOException {}", e);
                            throw new DaoException("findUserById at find all orders  DAOException " + e);
                        }
                    }
                    if (!orderRecipient.isEmpty()) {
                        order = Optional.of(new Order(orderId, idOrder, product, status, detailsId, details_id, details,
                                detailsDate, orderCreator.get(), orderRecipient.get()));
                    } else {
                        order = Optional.of(new Order(orderId, idOrder, product, status, detailsId, details_id, details,
                                detailsDate, orderCreator.get()));
                    }
                    list.add(order);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "findOrder SqlException {}", e);
                throw new DaoException("findOrder SqlException " + e);
            }
        }
        return list;
    }

    @Override
    public void addOrder(long orderId,long productId, Order.OrderStatus status, long detailsId) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        Optional<Long> details_id = findOrderDetails(detailsId);
        if (details_id.isPresent()) {
            String query = factory.addOrderQuery(orderId,productId, status, details_id.get());
            Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
            if (optionalConnection.isPresent()) {
                try (ProxyConnection connection = optionalConnection.get();
                     PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.executeUpdate();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "createOrder SqlException {}", e);
                    throw new DaoException("createOrder SqlException " + e);
                }
            }
        }
    }

    @Override
    public void addOrder(long orderId,long productId, Order.OrderStatus status, long detailsId, User creator) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        Optional<Long> details_id = findOrderDetails(detailsId);
        if (details_id.isPresent()) {
            String query = factory.addOrderQuery(orderId,productId, status, details_id.get(), creator);
            Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
            if (optionalConnection.isPresent()) {
                try (ProxyConnection connection = optionalConnection.get();
                     PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.executeUpdate();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "createOrder SqlException {}", e);
                    throw new DaoException("createOrder SqlException " + e);
                }
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
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
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
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "deleteOrder SqlException {}", e);
                throw new DaoException("deleteOrder SqlException " + e);
            }
        }
    }

    @Override
    public Optional<Long> findLastOrderId() throws DaoException {
        Optional<Long> details_id = Optional.empty();
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.findLastOrderId();
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    details_id = Optional.of(resultSet.getLong(ID_DETAILS_COLUMN));
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "findLastOrderId SqlException {}", e);
                throw new DaoException("findLastOrderId SqlException " + e);
            }
        }
        return details_id;
    }


    @Override
    public void changeOrderStatus(Long id, Order.OrderStatus status) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.changeOrderStatusQuery(id, status);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "changeOrderStatus SqlException {}", e);
                throw new DaoException("changeOrderStatus SqlException " + e);
            }
        }
    }

    @Override
    public void changeOrderRecipient(Long id, Long userId) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.changeOrderRecipientQuery(id, userId);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "changeOrderRecipient SqlException {}", e);
                throw new DaoException("changeOrderRecipient SqlException " + e);
            }
        }
    }


    public boolean addOrderDetails(long detailsId, String details) throws DaoException {
        OrderQueryFactory factory = new OrderQueryFactory();
        String query = factory.addOrderDetailsQuery(detailsId, details);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "addOrderDetails SqlException {}", e);
                throw new DaoException("addOrderDetails SqlException " + e);
            }
        }
        return true;
    }

    public Optional<Long> findOrderDetails(long detailsId) throws DaoException{
        Optional<Long> details_id = Optional.empty();
        OrderQueryFactory factory = new OrderQueryFactory();
        String queryToFind = factory.findOrdersDetailsQuery(detailsId);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        try (ProxyConnection connection = optionalConnection.get();
             PreparedStatement preparedStatement = connection.prepareStatement(queryToFind);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                details_id = Optional.of(resultSet.getLong(ID_DETAILS_COLUMN));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "findOrderDetails SqlException {}", e);
            throw new DaoException("findOrderDetails SqlException " + e);
        }
        return details_id;
    }
}
