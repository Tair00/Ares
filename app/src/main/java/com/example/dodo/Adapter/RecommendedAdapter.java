package com.example.dodo.Adapter;

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
import com.example.dodo.Activity.IntroActivity;

import com.example.dodo.Domain.OrderDomain;
import com.example.ares.R;

import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder>{
    ArrayList<OrderDomain> orderDomains;
    Context context;
    public RecommendedAdapter(ArrayList<OrderDomain> orderDomains) {
        this.orderDomains = orderDomains;
        this.context = context;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recomended,parent,false);
        return new RecommendedAdapter.ViewHolder(inflate);


    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){

        holder.title.setText(orderDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(orderDomains.get(position).getFee()));


        int drawableReourceId =holder.itemView.getContext().getResources()
                .getIdentifier(orderDomains.get(position).getPic(),"drawable",holder.itemView.getContext()
                        .getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableReourceId).into(holder.pic);
        holder.addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), IntroActivity.ShowDetailActivity.class);
            intent.putExtra("object", orderDomains.get(position));
            holder.itemView.getContext().startActivity(intent);
//!!!!
        });

    }
    @Override
    public int getItemCount(){return orderDomains.size() ;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        ImageView addBtn;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic= itemView.findViewById(R.id.pic);
            fee = itemView.findViewById(R.id.fee);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
