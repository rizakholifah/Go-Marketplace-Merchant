package com.rizwanindustries.gomarketplacemerchant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rizwanindustries.gomarketplacemerchant.Adapter.SlideImageProductAdapter;
import com.rizwanindustries.gomarketplacemerchant.Model.Product;
import com.rizwanindustries.gomarketplacemerchant.Model.SlideImageProduct;
import com.rizwanindustries.gomarketplacemerchant.R;

import java.util.List;

public class DescriptionProduct extends AppCompatActivity {

    private ViewPager vpImageDesc;
    private RecyclerView rvProductDesc;
    private List<SlideImageProduct> slideImage;
    private ImageView slideImagePriview, imgPriview;
    private TextView tvProduct, tvPrice, tvSlug, tvQty, tvMerchId, tvMerchName, tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_product);

        initial();
        //setAdpater();
        //Product product = getIntent().getParcelableExtra("data");
        //Toast.makeText(this, String.valueOf(product), Toast.LENGTH_LONG).show();

        setData();


    }

    public void initial(){
        //vpImageDesc = findViewById(R.id.slider_image_product_desc);
        imgPriview = findViewById(R.id.img_product_desc);
        tvProduct = findViewById(R.id.desc_product_name);
        tvPrice = findViewById(R.id.desc_price);
        tvSlug = findViewById(R.id.desc_slug);
        tvQty = findViewById(R.id.desc_product_qty);
        tvMerchId = findViewById(R.id.desc_merchant_id);
        tvMerchName = findViewById(R.id.desc_merchant_name);
        tvDesc = findViewById(R.id.desc_product);
        slideImagePriview = findViewById(R.id.image_slide);
    }

    public void setData(){
        Product product = getIntent().getParcelableExtra("data");
        String baeUrl = "http://210.210.154.65:4444/storage/";
        String url = baeUrl+product.getProductImage();

        if (product.getProductImage() != null){
            Glide.with(this).load(url).into(imgPriview);
            //Toast.makeText(this, product.getProductImage(), Toast.LENGTH_LONG).show();
        }
        Glide.with(this).load(url).into(imgPriview);

        tvProduct.setText(String.valueOf(product.getProductName()));
        tvPrice.setText(String.valueOf(product.getProductPrice()));
        tvSlug.setText(String.valueOf(product.getProductSlug()));
        tvQty.setText(String.valueOf(product.getProductQty()));
        tvMerchId.setText(String.valueOf(product.getMerchant().getMerchantId()));
        tvMerchName.setText(String.valueOf(product.getMerchant().getMerchantName()));
        tvDesc.setText(String.valueOf(product.getProductDesc()));
    }

    public void setAdpater(){
        SlideImageProductAdapter sliderAdapter = new SlideImageProductAdapter(this, slideImage);
        sliderAdapter.getCount();
        Toast.makeText(getApplicationContext(), String.valueOf(sliderAdapter), Toast.LENGTH_LONG).show();
        vpImageDesc.setAdapter(sliderAdapter);
    }
}
