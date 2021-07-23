package com.epam.webch.model.dao.impl;

import com.epam.webch.exception.DaoException;
import com.epam.webch.model.connection.ConnectionPool;
import com.epam.webch.model.connection.ProxyConnection;
import com.epam.webch.model.dao.ProductDao;
import com.epam.webch.model.dao.query.ProductQueryFactory;
import com.epam.webch.model.entity.product.Product;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    private static final ProductDao instance = new ProductDaoImpl();
    private static final Logger logger = LogManager.getLogger();

    //products columns start
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String PRICE_COLUMN = "price";
    private static final String IN_STOCK_COLUMN = "inStock";
    //products columns end

    private ProductDaoImpl(){

    }

    public static ProductDao getInstance(){
        return instance;
    }

    @Override
    public void addProduct(String name,int price) throws DaoException {
        ProductQueryFactory factory = new ProductQueryFactory();
        String query = factory.addProductQuery(name, price);
        Optional<ProxyConnection> optionalProxyConnection = ConnectionPool.getInstance().getConnection();
        if (optionalProxyConnection.isPresent()) {
            try (ProxyConnection proxyConnection = optionalProxyConnection.get();
                 Statement statement = proxyConnection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "createProduct SqlException {}", e);
                throw new DaoException("createProduct SqlException " + e);
            }
        }
    }

    @Override
    public Optional<Product> findProductById(Long id) throws DaoException {
        Optional<Product> product = Optional.empty();
        ProductQueryFactory factory = new ProductQueryFactory();
        String query = factory.findProductByIdQuery(id);
        Optional<ProxyConnection> optionalProxyConnection = ConnectionPool.getInstance().getConnection();
        if (optionalProxyConnection.isPresent()) {
            try (ProxyConnection proxyConnection = optionalProxyConnection.get();
                 Statement statement = proxyConnection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Long productId = resultSet.getLong(ID_COLUMN);
                    String name = resultSet.getString(NAME_COLUMN);
                    Integer price = resultSet.getInt(PRICE_COLUMN);
                    Integer isInStock = resultSet.getInt(IN_STOCK_COLUMN);
                    product = Optional.of(new Product(productId, name, price, isInStock));
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "getProductById SqlException {}", e);
                throw new DaoException("getProductById SqlException " + e);
            }
        }
        return product;
    }

    @Override
    public void deleteProduct(Product product) throws DaoException {
        ProductQueryFactory factory = new ProductQueryFactory();
        String query = factory.deleteProductQuery(product);
        Optional<ProxyConnection> optionalProxyConnection = ConnectionPool.getInstance().getConnection();
        if (optionalProxyConnection.isPresent()) {
            try (ProxyConnection proxyConnection = optionalProxyConnection.get();
                 Statement statement = proxyConnection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "deleteProduct SqlException {}", e);
                throw new DaoException("deleteProduct SqlException " + e);
            }
        }
    }

    @Override
    public void reallyDeleteProduct(Product product) throws DaoException {
        ProductQueryFactory factory = new ProductQueryFactory();
        String query = factory.reallyDeleteProductQuery(product);
        Optional<ProxyConnection> optionalProxyConnection = ConnectionPool.getInstance().getConnection();
        if (optionalProxyConnection.isPresent()) {
            try (ProxyConnection proxyConnection = optionalProxyConnection.get();
                 Statement statement = proxyConnection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "deleteProduct SqlException {}", e);
                throw new DaoException("deleteProduct SqlException " + e);
            }
        }
    }

    @Override
    public void changeProductPrice(Long id, int price) throws DaoException {
        ProductQueryFactory factory = new ProductQueryFactory();
        String query = factory.changeProductPriceQuery(id, price);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "changeProductRole SqlException {}", e);
                throw new DaoException("changeProductRole SqlException "+ e);
            }
        }
    }

    @Override
    public void changeProductName(Long id, String name) throws DaoException {
        ProductQueryFactory factory = new ProductQueryFactory();
        String query = factory.changeProductNameQuery(id,name);
        Optional<ProxyConnection> optionalConnection = ConnectionPool.getInstance().getConnection();
        if (optionalConnection.isPresent()) {
            try (ProxyConnection connection = optionalConnection.get();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "changeProductRole SqlException {}", e);
                throw new DaoException("changeProductRole SqlException "+ e);
            }
        }
    }

    @Override
    public List<Optional<Product>> findAllProducts() throws DaoException {
        List<Optional<Product>> products = new ArrayList<>();
        ProductQueryFactory factory = new ProductQueryFactory();
        String query = factory.findAllProductsQuery();
        Optional<ProxyConnection> optionalProxyConnection = ConnectionPool.getInstance().getConnection();
        if (optionalProxyConnection.isPresent()) {
            Optional<Product> product;
            try (ProxyConnection proxyConnection = optionalProxyConnection.get();
                 Statement statement = proxyConnection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Long productId = resultSet.getLong(ID_COLUMN);
                    String name = resultSet.getString(NAME_COLUMN);
                    Integer price = resultSet.getInt(PRICE_COLUMN);
                    Integer isInStock = resultSet.getInt(IN_STOCK_COLUMN);
                    product = Optional.of(new Product(productId, name, price, isInStock));
                    products.add(product);
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR, "createProduct SqlException {}", e);
                throw new DaoException("getAllProducts SqlException " + e);
            }
        }
        return products;
    }
}
