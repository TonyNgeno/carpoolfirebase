package com.example.tony.ridealong.Model;

public class Rides {
    private String Start_Point,Destination,Date,No_of_Seats,Price_per_seat;

    public Rides() {
    }

    public Rides(String Start_Point, String Destination, String date, String Mo_of_Seats, String Price_per_seat) {
        Start_Point = Start_Point;
        Destination = Destination;
        Date = date;
        No_of_Seats = Mo_of_Seats;
        Price_per_seat = Price_per_seat;
    }

    public String getStart_Point() {
        return Start_Point;
    }

    public void setStart_Poinr(String start_Poinr) {
        Start_Point = start_Poinr;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNo_of_Seats() {
        return No_of_Seats;
    }

    public void setNo_of_Seats(String no_of_Seats) {
        No_of_Seats = no_of_Seats;
    }

    public String getPrice_per_seat() {
        return Price_per_seat;
    }

    public void setPrice_per_seat(String price_per_seat) {
        Price_per_seat = price_per_seat;
    }
}
