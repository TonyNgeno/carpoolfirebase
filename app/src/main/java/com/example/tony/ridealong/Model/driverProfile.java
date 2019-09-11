package com.example.tony.ridealong.Model;

public class driverProfile {

    private  String mCarName,mIdDetail,mDrivingLicence,mNumberPlate;

    public driverProfile() {
    }

    public driverProfile(String mCarName, String mIdDetail, String mDrivingLicence, String mNumberPlate) {

        this.mCarName = mCarName;
        this.mIdDetail = mIdDetail;
        this.mDrivingLicence = mDrivingLicence;
        this.mNumberPlate = mNumberPlate;
    }

    public String getmCarName() {

        return mCarName;
    }

    public void setmCarName(String mCarName) {

        this.mCarName = mCarName;
    }

    public String getmIdDetail() {

        return mIdDetail;
    }

    public void setmIdDetail(String mIdDetail) {

        this.mIdDetail = mIdDetail;
    }

    public String getmDrivingLicence() {

        return mDrivingLicence;
    }

    public void setmDrivingLicence(String mDrivingLicence) {

        this.mDrivingLicence = mDrivingLicence;
    }

    public String getmNumberPlate() {

        return mNumberPlate;
    }

    public void setmNumberPlate(String mNumberPlate) {

        this.mNumberPlate = mNumberPlate;
    }
}
