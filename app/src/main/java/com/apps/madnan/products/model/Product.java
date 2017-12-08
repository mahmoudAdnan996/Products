package com.apps.madnan.products.model;

/**
 * Created by mahmoud adnan on 12/7/2017.
 */

public class Product {

    private int id;
    private String price;
    private String productDescription;
    private Image image;

    public Product(String title, String overview) {
        this.price = title;
        this.productDescription = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price='" + price + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';

    }
}
