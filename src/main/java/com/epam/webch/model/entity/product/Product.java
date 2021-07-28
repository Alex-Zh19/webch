package com.epam.webch.model.entity.product;


public class Product {
    private long id;
    private String name;
    private int price;
    private String description;
    private int isInStock;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(int isInStock) {
        this.isInStock = isInStock;
    }

    public Product(long id, String name, int price, String description, int isInStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description=description;
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
        return id == product.id && price == product.price && name.equals(product.name)&&
                description.equals(product.description)
                && isInStock == product.isInStock;
    }

    @Override
    public int hashCode() {
        int multiplier = 31;
        int result = 0;
        Long idLong = id;
        result += multiplier * idLong.hashCode();
        result = multiplier * result + name.hashCode();
        result = multiplier * result + price;
        result = multiplier * result + description.hashCode();
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
        result.append(", description=");
        result.append(description);
        result.append(", inStock=");
        result.append(isInStock);
        result.append("}");
        return result.toString();
    }
}
