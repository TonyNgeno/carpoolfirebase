package com.example.tony.ridealong.Model;

public class OfferRide {

    private String editTextDest,editDate,editTextSeatNo,editTextSeatPrice;

    public OfferRide() {
    }

    public OfferRide(String editTextDest, String editDate, String editTextSeatNo, String editTextSeatPrice) {
        this.editTextDest = editTextDest;
        this.editDate = editDate;
        this.editTextSeatNo = editTextSeatNo;
        this.editTextSeatPrice = editTextSeatPrice;
    }

    public String getEditTextDest() {
        return editTextDest;
    }

    public void setEditTextDest(String editTextDest) {
        this.editTextDest = editTextDest;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getEditTextSeatNo() {
        return editTextSeatNo;
    }

    public void setEditTextSeatNo(String editTextSeatNo) {
        this.editTextSeatNo = editTextSeatNo;
    }

    public String getEditTextSeatPrice() {
        return editTextSeatPrice;
    }

    public void setEditTextSeatPrice(String editTextSeatPrice) {
        this.editTextSeatPrice = editTextSeatPrice;
    }
}
