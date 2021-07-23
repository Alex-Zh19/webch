package com.epam.webch.model.entity.product;


public class Product {
    long id;
    String name;
    int price;
    int isInStock;


    public Product(long id, String name, int price, int isInStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isInStock = isInStock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int IsInStock() {
        return isInStock;
    }

    public void setInStock(int inStock) {
        isInStock = inStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && name.equals(product.name) && isInStock == product.isInStock;
    }

    @Override
    public int hashCode() {
        int multiplier = 31;
        int result = 0;
        Long idLong = id;
        result += multiplier * idLong.hashCode();
        result = multiplier * result + name.hashCode();
        result = multiplier * result + price;
        result = multiplier * result + isInStock;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Product{id=");
        result.append(id);
        result.append(", name=");
        result.append(name);
        result.append(", price=");
        result.append(price);
        result.append(", inStock=");
        result.append(isInStock);
        result.append("}");
        return result.toString();
    }
}
