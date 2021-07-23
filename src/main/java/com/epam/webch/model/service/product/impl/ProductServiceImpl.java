package com.epam.webch.model.service.product.impl;

import com.epam.webch.exception.DaoException;
import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.dao.ProductDao;
import com.epam.webch.model.dao.impl.ProductDaoImpl;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;
import com.epam.webch.model.service.product.ProductService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private static final Logger logger= LogManager.getLogger();
    private ProductService instance;
    private ProductDao productDao= ProductDaoImpl.getInstance();

    public ProductService getInstance(){
        if(instance==null){
            instance=new ProductServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean addProduct(String name, int price) throws ServiceException {
        try {
            productDao.addProduct(name, price);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at addProduct method. {}", e);
            throw new ServiceException("DaoException at addProduct method. " + e);
        }
    }

    @Override
    public Optional<Product> findProductById(Long id) throws ServiceException {
        Optional<Product> product;
        try {
            product = productDao.findProductById(id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at findProductById method. {}", e);
            throw new ServiceException("DaoException at findProductById method. " + e);
        }
        return product;
    }

    @Override
    public boolean deleteProduct(Product product) throws ServiceException {
        try {
            productDao.deleteProduct(product);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at deleteProduct method. {}", e);
            throw new ServiceException("DaoException at deleteProduct method. " + e);
        }
    }


    @Override
    public boolean changeProductPrice(Long id, int price) throws ServiceException {
        try {
            productDao.changeProductPrice(id, price);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at findProductById method. {}", e);
            throw new ServiceException("DaoException at findProductById method. " + e);
        }
    }

    @Override
    public boolean changeProductName(Long id, String name) throws ServiceException {
        try {
            productDao.changeProductName(id, name);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at changeProductName method. {}", e);
            throw new ServiceException("DaoException at changeProductName method. " + e);
        }
    }

    @Override
    public List<Optional<Product>> findAllProducts() throws ServiceException {
        List<Optional<Product>>products;
        try {
            products = productDao.findAllProducts();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at findAllProducts method. {}", e);
            throw new ServiceException("DaoException at findAllProducts method. " + e);
        }
        return products;
    }



    @Override
    public boolean reallyDeleteProduct(Product product) throws ServiceException {
        try {
            productDao.reallyDeleteProduct(product);
            return true;
        } catch (DaoException e) {
            logger.log(Level.ERROR, "DaoException at reallyDeleteProduct method. {}", e);
            throw new ServiceException("DaoException at reallyDeleteProduct method. " + e);
        }

    }


}
