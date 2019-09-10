package com.example.tony.ridealong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DriverHomePage extends AppCompatActivity {
    CardView offerridebtn,editprofdetails,backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home_page);

        offerridebtn = findViewById(R.id.offerridebtn);
        editprofdetails = findViewById(R.id.editprofdetails);
        backbtn = findViewById(R.id.backbtn);

        offerridebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriverHomePage.this, OfferRide.class);
                startActivity(intent);
                finish();
            }
        });

        editprofdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriverHomePage.this, ChooseActivity.class);
                startActivity(intent);
                finish();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriverHomePage.this, ChooseActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
