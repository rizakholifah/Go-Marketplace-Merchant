package com.rizwanindustries.gomarketplacemerchant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.rizwanindustries.gomarketplacemerchant.Model.AdvertisePager;
import com.rizwanindustries.gomarketplacemerchant.Model.Product;
import com.rizwanindustries.gomarketplacemerchant.R;

import java.util.ArrayList;
import java.util.List;

public class AdvertiseAdapter extends PagerAdapter {

    private Context context;
    private List<AdvertisePager> models;
    private ArrayList<Product> product = new ArrayList<>();

    public AdvertiseAdapter(Context context, List<AdvertisePager> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.advertise_card_layout, container, false);

        ImageView imgCard;
        TextView tvProduct, tvPrice;

        imgCard = view.findViewById(R.id.img_advertise);
        tvProduct = view.findViewById(R.id.tv_product_add);
        tvPrice = view.findViewById(R.id.tv_price_add);

        imgCard.setImageResource(models.get(position).getImage());
        tvProduct.setText(models.get(position).getProductName());
        tvPrice.setText(models.get(position).getPrice());

        /*String baseUrl = "http://210.210.154.65:4444/storage/";

        if(product.get(position).getProductImage() != null){
            String url = baseUrl+product.get(position).getProductImage();
            Glide.with(context)
                    .load(url)
                    .into(imgCard);
        }*/

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
