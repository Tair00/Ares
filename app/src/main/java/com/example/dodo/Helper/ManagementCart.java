package com.example.dodo.Helper;

import android.content.Context;

import com.example.dodo.Domain.OrderDomain;
import com.example.dodo.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

import android.widget.Toast;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertFood(OrderDomain item){
        ArrayList<OrderDomain> listOrder= getListCart();
        boolean existAlready=false;
        int n=0;
        for(int i = 0; i<listOrder.size();i++){
            if(listOrder.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=i;
                break;
            }
        }
        if(existAlready){
            listOrder.get(n).setNumberInCart(item.getNumberInCart());


        }else {
            listOrder.add(item);
        }
        tinyDB.putListObject("CardList",listOrder);
        Toast.makeText(context, "Ваш товар добавили", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<OrderDomain> getListCart(){
        return tinyDB.getListObject("CardList");

    }
    public void minusNumberFood(ArrayList<OrderDomain>listorder, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(listorder.get(position).getNumberInCart()==1){
            listorder.remove(position);
        }
        else {
            listorder.get(position).setNumberInCart(listorder.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CardList",listorder);
        changeNumberItemsListener.changed();
    }

    public void plusNumberFood(ArrayList<OrderDomain> listorder, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listorder.get(position).setNumberInCart(listorder.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CardList",listorder);
        changeNumberItemsListener.changed();
    }


    public Double getTotalFee(){
        ArrayList<OrderDomain>listorder2=getListCart();
        double fee=0;
        for (int i = 0 ; i< listorder2.size();i++){
            fee=fee+(listorder2.get(i).getFee()*listorder2.get(i).getNumberInCart());
        }
        return fee;
    }
}