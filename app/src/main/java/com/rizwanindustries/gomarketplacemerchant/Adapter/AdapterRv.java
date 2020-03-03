package com.rizwanindustries.gomarketplacemerchant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rizwanindustries.gomarketplacemerchant.DescriptionProduct;
import com.rizwanindustries.gomarketplacemerchant.Model.Product;
import com.rizwanindustries.gomarketplacemerchant.R;

import java.util.ArrayList;

public class AdapterRv extends RecyclerView.Adapter<AdapterRv.ViewHolder>{

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Product> product = new ArrayList<>();

    public AdapterRv(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_layout_view_2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.txtName.setText(product.get(position).getProductName());
        holder.txtMerchant.setText(product.get(position).getMerchant().getMerchantName());
        holder.txtPrice.setText(product.get(position).getProductPrice());
        holder.txtDesc.setText(product.get(position).getProductDesc());
        String baseUrl = "http://210.210.154.65:4444/storage/";

        if(product.get(position).getProductImage() != null){
            String url = baseUrl+product.get(position).getProductImage();
            Glide.with(context)
                    .load(url)
                    .into(holder.imgProduct);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DescriptionProduct.class);
                intent.putExtra("data", product.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtMerchant, txtPrice, txtDesc;
        private ImageView imgProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_product_name);
            txtMerchant = itemView.findViewById(R.id.txt_merchant);
            txtPrice = itemView.findViewById(R.id.txt_price);
            txtDesc = itemView.findViewById(R.id.txt_desc);
            imgProduct = itemView.findViewById(R.id.img_product);


        }
    }

    public void addData(ArrayList<Product> products){
        this.product = products;
    }
}

