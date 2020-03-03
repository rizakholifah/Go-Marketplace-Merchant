package com.rizwanindustries.gomarketplacemerchant.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private int productId;
    private String productName;
    private String productSlug;
    private String productQty;
    private String productPrice;
    private String productDesc;
    private String productImage;
    private Merchant merchant;
    private Category category;

    protected Product(Parcel in) {
        productId = in.readInt();
        productName = in.readString();
        productSlug = in.readString();
        productQty = in.readString();
        productPrice = in.readString();
        productDesc = in.readString();
        productImage = in.readString();
        merchant = in.readParcelable(Merchant.class.getClassLoader());
        category = in.readParcelable(Category.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductSlug() {
        return productSlug;
    }

    public String getProductQty() {
        return productQty;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getProductImage() {
        return productImage;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public Category getCategory() {
        return category;
    }

    public Product(int productId, String productName, String productSlug, String productQty, String productPrice, String productDesc, String productImage, Merchant merchant, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.productSlug = productSlug;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.productImage = productImage;
        this.merchant = merchant;
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productId);
        dest.writeString(productName);
        dest.writeString(productSlug);
        dest.writeString(productQty);
        dest.writeString(productPrice);
        dest.writeString(productDesc);
        dest.writeString(productImage);
        dest.writeParcelable(merchant, flags);
        dest.writeParcelable(category, flags);
    }
}
