package com.example.dodo.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dodo.Activity.IntroActivity;
import com.example.dodo.Domain.OrderDomain;
import com.example.ares.R;

import java.util.ArrayList;


public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.ProductViewHolder> {
    Context context;
    ArrayList<OrderDomain> products;

    public PriceAdapter(Context context, ArrayList<OrderDomain> products) {
        this.context = context;
        this.products = products;
    }



    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productsItems = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
        return new PriceAdapter.ProductViewHolder(productsItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.productTitles.setText(products.get(position).getTitle());
        holder.productPrice.setText(String.valueOf(products.get(position).getFee()));

        int imageId = holder.itemView.getContext().getResources()
                .getIdentifier( products.get(position).getPic(),"drawable",holder.itemView.getContext()
                        .getPackageName());
        Glide.with(holder.itemView.getContext()).load(imageId).into(holder.productImage);
        holder.productTitles.setText(products.get(position).getTitle());
        holder.productPrice.setText(String.valueOf(products.get(position).getFee()));
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)context,
                        new Pair<View, String>(holder.productImage,"productImage"));
                Intent intent = new Intent(holder.itemView.getContext(), IntroActivity.ShowDetailActivity.class);
                intent.putExtra("object",products.get(position));
                holder.itemView.getContext().startActivity(intent,options.toBundle());

            }

        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView productTitles, productPrice;




        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage= itemView.findViewById(R.id.pic);
            productTitles= itemView.findViewById(R.id.title);
            productPrice= itemView.findViewById(R.id.fee);

        }
    }

}