package com.rizwanindustries.gomarketplacemerchant;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.rizwanindustries.gomarketplacemerchant.Adapter.AdapterRv;
import com.rizwanindustries.gomarketplacemerchant.Adapter.AdvertiseAdapter;
import com.rizwanindustries.gomarketplacemerchant.Model.AdvertisePager;
import com.rizwanindustries.gomarketplacemerchant.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    AdapterRv adapterRv = new AdapterRv(getContext());
    RequestQueue requestQueue;
    List<AdvertisePager> listImage;
    ViewPager sliderPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

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


        requestQueue = Volley.newRequestQueue(getContext());
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
