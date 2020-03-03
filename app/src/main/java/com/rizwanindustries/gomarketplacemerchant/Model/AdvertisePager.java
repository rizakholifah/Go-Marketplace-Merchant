package com.rizwanindustries.gomarketplacemerchant.Model;

public class AdvertisePager {

    private int image;
    private String productName;
    private String price;

    public AdvertisePager(int image, String productName, String price) {
        this.image = image;
        this.productName = productName;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }
}
