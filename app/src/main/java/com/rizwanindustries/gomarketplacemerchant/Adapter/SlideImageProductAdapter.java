package com.rizwanindustries.gomarketplacemerchant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.rizwanindustries.gomarketplacemerchant.Model.SlideImageProduct;
import com.rizwanindustries.gomarketplacemerchant.R;

import java.util.List;

public class SlideImageProductAdapter extends PagerAdapter {

    private Context context;
    private List<SlideImageProduct> imageSlide;


    public SlideImageProductAdapter(Context context, List<SlideImageProduct> imageSlide) {
        this.context = context;
        this.imageSlide = imageSlide;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slider_image,container,false);

        ImageView slideImage = slideLayout.findViewById(R.id.image_slide);
        slideImage.setImageResource(imageSlide.get(position).getProductImage());

        container.addView(slideLayout);

        return slideLayout;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
