package com.example.dodo.Activity;

public class payResult {
    private String addres;

    public String getAddres() {
        return addres;
    }

    public String getPay() {
        return pay;
    }

    public String getTotalTxt() {
        return totalTxt;
    }

    private String pay;
    private String totalTxt;

    public payResult(String addres, String pay, String totalTxt) {
        this.addres = addres;
        this.pay = pay;
        this.totalTxt = totalTxt;
    }
}
