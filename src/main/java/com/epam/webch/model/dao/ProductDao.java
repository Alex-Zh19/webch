package com.epam.webch.model.dao;

import com.epam.webch.exception.DaoException;
import com.epam.webch.model.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void addProduct(String name,int price,String description) throws DaoException;
    Optional<Product> findProductById(Long id) throws DaoException;
    void deleteProduct(Product product) throws DaoException;
    void reallyDeleteProduct(Product product) throws DaoException;
    void changeProductPrice(Long id,int price) throws DaoException;
    void changeProductName(Long id,String name) throws DaoException;
    void changeProductDescription(Long id,String description) throws DaoException;
    void changeProductInStock(Long id,int inStock) throws DaoException;
    List<Optional<Product>> findAllProducts() throws DaoException;


}
