package com.example.dodo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ares.R;
import com.example.dodo.Adapter.CartListAdapter;
import com.example.dodo.Helper.ManagementCart;
import com.example.dodo.Interface.ChangeNumberItemsListener;
import com.example.dodo.Interface.RetrofitInterface;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt,taxTxt,deliveryTxt ;
    public TextView totalTxt;

    private double tax;
    private ScrollView scrollView;
    private ConstraintLayout orderbtn;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    private String BASE_URL = "https://ares-backend-6bdi.onrender.com";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        managementCart= new ManagementCart(this);

        findViewById(R.id.orderbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePayDialog();
            }
        });
        initView();
        initList();
        bottomNavigation();
        calculateCard();
    }


    protected void bottomNavigation(){
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn =findViewById(R.id.cartBtn);


        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,CartActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,MainActivity.class));
            }
        });

    }
    protected void handlePayDialog() {
        View view = getLayoutInflater().inflate(R.layout.pay_activity, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();
        Button paybtn = view.findViewById(R.id.pay);

        final EditText payEdit = view.findViewById(R.id.payEdit);
        final EditText addressEdit=view.findViewById(R.id.addressEdit);
        String title = "заказ принят";
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();

                map.put("pay", payEdit.getText().toString());
                map.put("totalTxt", totalTxt.getText().toString());
                map.put("address", addressEdit.getText().toString());

                map.put("title", title);
                Call<Void> call = retrofitInterface.executePay(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(CartActivity.this,
                                "Оплата прошла успешно )", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(CartActivity.this,
                                "Оплата не прошла", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
    protected void initList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager( linearLayoutManager);
        adapter= new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){

            scrollView.setVisibility(View.GONE);

        }
        else {

            scrollView.setVisibility(View.VISIBLE);
        }
    }

    protected void calculateCard() {
        double percentTax = 0.03;
        double delivery = 500;
        tax = Math.round((managementCart.getTotalFee()*percentTax)*100)/100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery)*100.0)/100.0;
        double itemTotal = Math.round((managementCart.getTotalFee()*100.0)/100.0);
        totalFeeTxt.setText(itemTotal+" руб");
        if (itemTotal >= 7000){
            delivery = 0;
        }
        taxTxt.setText(tax+" руб");
        deliveryTxt.setText(delivery +" руб" );
        totalTxt.setText(total+" руб");

    }

    protected void initView() {

        orderbtn =findViewById(R.id.orderbtn);
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerViewList=findViewById(R.id.view);
        scrollView=findViewById(R.id.scrollView);

    }
}