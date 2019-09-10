package com.example.tony.ridealong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class OfferRide extends AppCompatActivity {

    EditText editTextStart,editTextDest,editDate,editTextSeatNo,editTextSeatPrice;
    TextView textOfferARide;
    CardView cardviewbtn;
    FirebaseDatabase database;
    DatabaseReference table_rides;
    String mTextStart,mTextDest,mDate,mTextSeatNo,mTextSeatPrice;
    private FirebaseAuth mAuth;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayofMonth;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_ride);

        //initialize firebase
        database = FirebaseDatabase.getInstance();
        table_rides = database.getReference("Rides"); //this your table name

        editTextStart = findViewById(R.id.editTextStart);
        editTextDest = findViewById(R.id.editTextDest);
        editDate = findViewById(R.id.editDate);
        editTextSeatNo = findViewById(R.id.editTextSeatNo);
        editTextSeatPrice = findViewById(R.id.editTextSeatPrice);
        textOfferARide = findViewById(R.id.textOfferARide);
        cardviewbtn = findViewById(R.id.cardviewbtn);


        cardviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OfferRide.this, DriverHomePage.class);
                startActivity(intent);
                finish();
            }
        });

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar= Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(OfferRide.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        editDate.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, dayofMonth);

                datePickerDialog.show();

            }
        });



        textOfferARide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editTextStart.getText().toString())){
                    Toast.makeText(OfferRide.this, "Enter Start Destination", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(editTextDest.getText().toString())){
                    Toast.makeText(OfferRide.this, "Enter Destination", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(editDate.getText().toString())){
                    Toast.makeText(OfferRide.this, "Please Enter Date", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(editTextSeatNo.getText().toString())){
                    Toast.makeText(OfferRide.this, "Please Enter Seat No !!!", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(editTextSeatPrice.getText().toString())){
                    Toast.makeText(OfferRide.this, "Enter Price!!!", Toast.LENGTH_SHORT).show();
                }

                final ProgressDialog progressDialog = new ProgressDialog(OfferRide.this);
                progressDialog.setMessage("Please wait ...");
                progressDialog.show();

                mTextStart = editTextStart.getText().toString();
                mTextDest = editTextDest.getText().toString();
                mDate = editDate.getText().toString();
                mTextSeatNo = editTextSeatNo.getText().toString();
                mTextSeatPrice = editTextSeatPrice.getText().toString();

                table_rides.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        com.example.tony.ridealong.Model.OfferRide offerRide = new com.example.tony.ridealong.Model.OfferRide(mTextDest,mDate,mTextSeatNo,mTextSeatPrice);
                        table_rides.child(mTextStart).setValue(offerRide);

                        Toast.makeText(OfferRide.this, "Welcome", Toast.LENGTH_SHORT).show();
                        final Intent mainIntent = new Intent(OfferRide.this, ShowRides.class);
                        OfferRide.this.startActivity(mainIntent);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        OfferRide.this.finish();



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });





    }
}
