package com.rizwanindustries.gomarketplacemerchant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.Gson;
import com.rizwanindustries.gomarketplacemerchant.Adapter.AdapterRv;
import com.rizwanindustries.gomarketplacemerchant.Adapter.AdvertiseAdapter;
import com.rizwanindustries.gomarketplacemerchant.Adapter.CategoriesAdapter;
import com.rizwanindustries.gomarketplacemerchant.Model.AdvertisePager;
import com.rizwanindustries.gomarketplacemerchant.Model.Category;
import com.rizwanindustries.gomarketplacemerchant.Model.Product;
import com.rizwanindustries.gomarketplacemerchant.Model.ProductErrorResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterRv adapterRv = new AdapterRv(this);
    RequestQueue requestQueue;
    List<AdvertisePager> listImage;
    ViewPager sliderPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();

        listImage = new ArrayList<>();

        listImage.add(new AdvertisePager(R.drawable.example1, "Example 1", "15000"));
        listImage.add(new AdvertisePager(R.drawable.example2, "Example 2", "15000"));
        listImage.add(new AdvertisePager(R.drawable.example3, "Example 3", "15000"));
        listImage.add(new AdvertisePager(R.drawable.example4, "Example 4", "15000"));
        listImage.add(new AdvertisePager(R.drawable.example5, "Example 5", "15000"));
        listImage.add(new AdvertisePager(R.drawable.example6, "Example 6", "15000"));
        listImage.add(new AdvertisePager(R.drawable.example7, "Example 7", "15000"));

        setAdapterVp();


        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://210.210.154.65:4444/api/products";
        JsonObjectRequest listRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    ArrayList<Product> products = new ArrayList<>();
                    JSONArray data = response.getJSONArray("data");
                    for(int i=0;i<data.length();i++){
                        Gson gson = new Gson();
                        Product product = gson.fromJson(data.getJSONObject(i).toString(),Product.class);
                        products.add(product);
                    }
                    setAdaperRv();
                    adapterRv.addData(products);
                    //Toast.makeText(getApplicationContext(), String.valueOf(adapterRv.getItemCount()), Toast.LENGTH_LONG).show();

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", error.getMessage());
            }
        });
        //getDataFromJson();

        requestQueue.add(listRequest);

    }

    public void initial(){
        recyclerView = findViewById(R.id.rv_list);
        sliderPager = findViewById(R.id.vp_slide_image);
    }

    public void setAdaperRv(){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterRv);
    }

    public void setAdapterVp(){

        AdvertiseAdapter adapterVp = new AdvertiseAdapter(this, listImage);
        sliderPager.setAdapter(adapterVp);
        //Toast.makeText(getApplicationContext(), String.valueOf(adapterVp.getCount()), Toast.LENGTH_LONG).show();
        sliderPager.setPadding(130,0,130,0);

    }

}
