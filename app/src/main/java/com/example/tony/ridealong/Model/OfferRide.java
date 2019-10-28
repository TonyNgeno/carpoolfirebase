package com.example.tony.ridealong.Model;

public class OfferRide {

    private String mTextDest, mTextSeatNo, mTextStart, mDate, mTextSeatPrice;

    public OfferRide() {
    }

    public OfferRide(String mTextDest, String mTextSeatNo, String mTextStart, String mDate, String mTextSeatPrice) {
        this.mTextDest = mTextDest;
        this.mTextSeatNo = mTextSeatNo;
        this.mTextStart = mTextStart;
        this.mDate = mDate;
        this.mTextSeatPrice = mTextSeatPrice;
    }

    public String getmTextDest() {
        return mTextDest;
    }

    public void setmTextDest(String mTextDest) {
        this.mTextDest = mTextDest;
    }

    public String getmTextSeatNo() {
        return mTextSeatNo;
    }

    public void setmTextSeatNo(String mTextSeatNo) {
        this.mTextSeatNo = mTextSeatNo;
    }

    public String getmTextStart() {
        return mTextStart;
    }

    public void setmTextStart(String mTextStart) {
        this.mTextStart = mTextStart;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTextSeatPrice() {
        return mTextSeatPrice;
    }

    public void setmTextSeatPrice(String mTextSeatPrice) {
        this.mTextSeatPrice = mTextSeatPrice;
    }
}
