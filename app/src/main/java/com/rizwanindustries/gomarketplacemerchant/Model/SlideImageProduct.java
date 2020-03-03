package com.rizwanindustries.gomarketplacemerchant.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SlideImageProduct implements Parcelable {

    private int productImage;

    public SlideImageProduct(int productImage) {
        this.productImage = productImage;
    }

    public int getProductImage() {
        return productImage;
    }

    protected SlideImageProduct(Parcel in) {
        productImage = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SlideImageProduct> CREATOR = new Creator<SlideImageProduct>() {
        @Override
        public SlideImageProduct createFromParcel(Parcel in) {
            return new SlideImageProduct(in);
        }

        @Override
        public SlideImageProduct[] newArray(int size) {
            return new SlideImageProduct[size];
        }
    };
}
