package com.example.tony.ridealong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PassengerHomePage extends AppCompatActivity {
    CardView searchbtn,editprofdetails,showridebtn,backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home_page);

        searchbtn = findViewById(R.id.searchbtn);
        editprofdetails = findViewById(R.id.editprofdetails);
        showridebtn = findViewById(R.id.showridebtn);
        backbtn = findViewById(R.id.backbtn);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PassengerHomePage.this, SearchDestination.class);
                startActivity(intent);
                finish();
            }
        });

        editprofdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PassengerHomePage.this, ChooseActivity.class);
                startActivity(intent);
                finish();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PassengerHomePage.this, ChooseActivity.class);
                startActivity(intent);
                finish();
            }
        });

        showridebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PassengerHomePage.this, ShowRides.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
