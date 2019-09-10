package com.example.tony.ridealong.Model;

public class Rides {
    private String item_title,item_start,item_dest,item_date,item_seats,priceperseat;

    public Rides() {
    }

    public Rides(String item_title, String item_start, String item_dest, String item_date, String item_seats, String priceperseat) {
        this.item_title = item_title;
        this.item_start = item_start;
        this.item_dest = item_dest;
        this.item_date = item_date;
        this.item_seats = item_seats;
        this.priceperseat = priceperseat;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_start() {
        return item_start;
    }

    public void setItem_start(String item_start) {
        this.item_start = item_start;
    }

    public String getItem_dest() {
        return item_dest;
    }

    public void setItem_dest(String item_dest) {
        this.item_dest = item_dest;
    }

    public String getItem_date() {
        return item_date;
    }
     public void setItem_date(String item_date) {
        this.item_date = item_date;
    }

    public String getItem_seats() {
        return item_seats;
    }

    public void setItem_seats(String item_seats) {
        this.item_seats = item_seats;
    }

    public String getPrice() {
        return priceperseat;
    }

    public void setPrice(String priceperseat) {
        this.priceperseat = priceperseat;
    }
}
