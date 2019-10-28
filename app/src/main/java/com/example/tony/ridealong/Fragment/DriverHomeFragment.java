package com.example.tony.ridealong.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.tony.ridealong.ChooseActivity;
import com.example.tony.ridealong.DriverMainActivity;
import com.example.tony.ridealong.Model.OfferRide;
import com.example.tony.ridealong.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class DriverHomeFragment extends Fragment {

    EditText editTextStart,editTextDest,editDate,editTextSeatNo,editTextSeatPrice;
    TextView textOfferARide;
    CardView cardview1,cardviewbtn;
    FirebaseDatabase database;
    DatabaseReference table_rides;
    String mTextStart,mTextDest,mDate,mTextSeatNo,mTextSeatPrice;
    private FirebaseAuth mAuth;
    private FirebaseUser mcurrentUser;
    DatePickerDialog datePickerDialog;
    TextView backbutton;
    int year;
    int month;
    int dayofMonth;
    Calendar calendar;
/*     int id = 1;*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_driverhome_layout,container,false);


        //initialize firebase
        database = FirebaseDatabase.getInstance();
        table_rides = database.getReference("Rides"); //this your table name

        editTextStart = rootview.findViewById(R.id.editTextStart);
        editTextDest = rootview.findViewById(R.id.editTextDest);
        editDate = rootview.findViewById(R.id.editDate);
        editTextSeatNo = rootview.findViewById(R.id.editTextSeatNo);
        editTextSeatPrice = rootview.findViewById(R.id.editTextSeatPrice);
        cardviewbtn = rootview.findViewById(R.id.cardviewbtn);
        cardview1 = rootview.findViewById(R.id.cardview1);



/*
        for (id = 1; id>0 ; id++)
*/



        cardviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar= Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayofMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        editDate.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, dayofMonth);

                datePickerDialog.show();

            }
        });


        cardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //editTextSeatPrice.setText("100");

                if(TextUtils.isEmpty(editTextStart.getText().toString()) || TextUtils.isEmpty(editTextDest.getText().toString())
                        || TextUtils.isEmpty(editDate.getText().toString() )|| TextUtils.isEmpty(editTextSeatNo.getText().toString() )||
                        TextUtils.isEmpty(editTextSeatPrice.getText().toString())){
                    Toast.makeText(getActivity(), "Empty fields", Toast.LENGTH_SHORT).show();
                }

                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Please wait ...");
                progressDialog.show();

                mTextStart = editTextStart.getText().toString();
                mTextDest = editTextDest.getText().toString();
                mDate = editDate.getText().toString();
                mTextSeatNo = editTextSeatNo.getText().toString();
                mTextSeatPrice = editTextSeatPrice.getText().toString();
                final DatabaseReference newPost = table_rides.push();

                /*table_rides.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        newPost.child("Start_Point").setValue(mTextStart);
                        newPost.child("Destination").setValue(mTextDest);
                        newPost.child("Date").setValue(mDate);
                        newPost.child("No_of_Seats").setValue(mTextSeatNo);
                        newPost.child("Price_per_seat").setValue(mTextSeatPrice);


                        newPost.child("uid").setValue(mcurrentUser.getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    Intent mainActivity = new Intent(getActivity(), DriverMainActivity.class);
                                    startActivity(mainActivity);
                                }
                            }
                        });



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
                table_rides.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        newPost.child("Start_Point").setValue(mTextStart);
                        newPost.child("Destination").setValue(mTextDest);
                        newPost.child("Date").setValue(mDate);
                        newPost.child("No_of_Seats").setValue(mTextSeatNo);
                        newPost.child("Price_per_seat").setValue(mTextSeatPrice);

                        /*
                        final String  mTextStart = editTextStart.getText().toString();
                        final String  mTextDest = editTextDest.getText().toString();
                        final String mDate = editDate.getText().toString();
                        final String mTextSeatNo = editTextSeatNo.getText().toString();
                        final String mTextSeatPrice = editTextSeatPrice.getText().toString();*/


                        //OfferRide save = new OfferRide(mTextDest, mTextSeatNo, mTextStart, mDate,mTextSeatPrice);
                        //table_rides.child(uid).setValue(save);

                        newPost.child("uid").setValue(mcurrentUser.getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    Intent mainActivity = new Intent(getActivity(), DriverMainActivity.class);
                                    startActivity(mainActivity);
                                }
                            }
                        });





                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            /*table_rides.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        newPost.child("Start_Point").setValue(mTextStart);
                        newPost.child("Destination").setValue(mTextDest);
                        newPost.child("Date").setValue(mDate);
                        newPost.child("No_of_Seats").setValue(mTextSeatNo);
                        newPost.child("Price_per_seat").setValue(mTextSeatPrice);


                        newPost.child("uid").setValue(mcurrentUser.getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    Intent mainActivity = new Intent(getActivity(), DriverMainActivity.class);
                                    startActivity(mainActivity);
                                }
                            }
                        });



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
            }
        });

        return rootview;
    }
}
