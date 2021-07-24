package com.epam.webch.model.dao.query;

import com.epam.webch.model.entity.product.Product;

import static com.epam.webch.model.dao.query.SQLQuery.*;

public class ProductQueryFactory {

    private static final String PRODUCTS = "products ";
    private static final String ALL_FIELDS_INSERT = "(name,price) ";
    private static final String ALL_FIELDS_FIND = "id,name,price,inStock ";
    private static final int ON_STOCK=1;
    private static final int NOT_ON_STOCK=0;

    public  String findAllProductsQuery(){
        StringBuilder query=new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND).append(FROM).append(PRODUCTS);
        return query.toString();
    }


    public  String addProductQuery(Product product) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(PRODUCTS).append(ALL_FIELDS_INSERT).append("values(\"");
        String productName = product.getName();
        int price = product.getPrice();
        query.append(productName).append("\"").append(",");
        query.append("\"").append(price).append("\")");
        return query.toString();
    }

    public  String addProductQuery(String name, int price) {
        StringBuilder query = new StringBuilder(INSERT);
        query.append(INTO).append(PRODUCTS).append(ALL_FIELDS_INSERT).append("values(\"");
        query.append(name).append("\"").append(",");
        query.append("\"").append(price).append("\")");
        return query.toString();
    }

    public  String deleteProductQuery(Product product) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(PRODUCTS).append(SET).append("inStock=").
                append("\"").append(0).append("\" ").append(WHERE).append("id=");
        query.append("\"").append(product.getId()).append("\"");
        return query.toString();
    }


    public  String reallyDeleteProductQuery(Product product) {
        StringBuilder query = new StringBuilder(DELETE);
        query.append(FROM).append(PRODUCTS).append(WHERE).append("id=").
                append("\"").append(product.getId()).append("\"");
        return query.toString();
    }

    public  String findProductByIdQuery(Long id) {
        StringBuilder query=new StringBuilder(SELECT);
        query.append(ALL_FIELDS_FIND);
        query.append(FROM).append(PRODUCTS).append(WHERE).append("id=").
                append("\"").append(id).append("\"");
        return query.toString();
    }

    public  String changeProductPriceQuery(Long id, int price) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(PRODUCTS).append(SET);
        query.append("price=").append("\"").append(price).append("\" ");
        query.append(WHERE).append("id=").append(id);
        return query.toString();
    }
    public  String changeProductNameQuery(Long id, String name) {
        StringBuilder query = new StringBuilder(UPDATE);
        query.append(PRODUCTS).append(SET);
        query.append("name=").append("\"").append(name).append("\"");
        query.append(WHERE).append("id=").append(id);
        return query.toString();
    }
}
