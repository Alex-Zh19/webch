package com.epam.webch.model.service.product;

import com.epam.webch.exception.ServiceException;
import com.epam.webch.model.entity.order.Order;
import com.epam.webch.model.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    boolean addProduct(String name,int price) throws ServiceException;
    Optional<Product> findProductById(Long id) throws ServiceException;
    boolean deleteProduct(Product product) throws ServiceException;
    boolean changeProductPrice(Long id,int price) throws ServiceException;
    boolean changeProductName(Long id,String name) throws ServiceException;
    List<Optional<Product>> findAllProducts() throws ServiceException;


    boolean reallyDeleteProduct(Product product) throws ServiceException;
}
