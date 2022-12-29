package com.example.dodo.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;


import com.example.ares.R;
import com.example.dodo.Domain.OrderDomain;
import com.example.dodo.Helper.ManagementCart;



public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ConstraintLayout startButton= findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this,ProfActivity.class));


            }
        });



    }


    public static class ShowDetailActivity extends AppCompatActivity {
        private TextView addToCart;
        private TextView titleTxt, feeTxt,descriptionTxt,numberOrderTxt,totalPriceTxt,starTxt,textWeightTxt,timeTxt;
        private ImageView plusBtn,minusBtn,picFood;
        private OrderDomain object;
        private int numberOrder = 1;
        private ManagementCart managementCart;


        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_detail);


            managementCart = new ManagementCart(this);
            initView();
            getBundle();
        }

        private void getBundle() {
            object = (OrderDomain)getIntent().getSerializableExtra("object");
            int drawableResourceId =this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
            Glide.with(this).load(drawableResourceId).into(picFood);
            titleTxt.setText(object.getTitle());
            feeTxt.setText(object.getFee()+"руб");
            descriptionTxt.setText(object.getDescription());
            numberOrderTxt.setText(String.valueOf(numberOrder));
            textWeightTxt.setText(object.getWeight()+" кг");
            starTxt.setText(object.getStar()+"");
            timeTxt.setText(object.getTime()+ " мин");
            totalPriceTxt.setText(Math.round(numberOrder * object.getFee())+"руб");

            plusBtn.setOnClickListener(v -> {
                numberOrder = numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText(Math.round(numberOrder * object.getFee())+"руб");
            });

            minusBtn.setOnClickListener(v -> {
                if(numberOrder>1){
                    numberOrder = numberOrder-1;

                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText(Math.round(numberOrder * object.getFee())+"руб");

            });
            addToCart.setOnClickListener(v -> {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            });
        }


        private void initView(){
            addToCart=findViewById(R.id.addToCartBtn);
            titleTxt = findViewById(R.id.titleTxt);
            feeTxt = findViewById(R.id.priceTxt);
            descriptionTxt = findViewById(R.id.descriptionTxt);
            numberOrderTxt = findViewById(R.id.numberItemTxt);
            plusBtn = findViewById(R.id.plusCardBtn);
            minusBtn= findViewById(R.id.minusCardBtn);
            picFood =findViewById(R.id.foodPic);
            totalPriceTxt =findViewById(R.id.TotalPriceTxt);
            starTxt= findViewById(R.id.startTxt);
            textWeightTxt = findViewById(R.id.textVicaloriesTxt);
            timeTxt = findViewById(R.id.timeTxt);
        }
    }
}