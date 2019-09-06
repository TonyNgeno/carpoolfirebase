package com.example.tony.ridealong;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.view.View;

public class ChooseActivity extends AppCompatActivity {

    CardView mDriver,mPassenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);

        mDriver = findViewById(R.id.driver);
        mPassenger = findViewById(R.id.passenger);


        mDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this, DriverLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this, PassengerLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

}

