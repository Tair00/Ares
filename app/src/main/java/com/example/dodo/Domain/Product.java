package com.example.dodo.Domain;
import java.io.Serializable;

public class Product implements Serializable {
    private String title;
    private int category;

    private String pic;
    private String description;
    private Double fee;
    private Double star;
    private int time;
    private Double weight;
    private int numberInCart;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public Product(String title, String pic, String description, Double fee, Double star, int time, Double weight,int category) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.star = star;
        this.time = time;
        this.weight = weight;
        this.category= category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}