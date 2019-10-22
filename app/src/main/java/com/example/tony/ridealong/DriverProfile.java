package com.example.tony.ridealong;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverProfile extends AppCompatActivity {

    EditText carname,iddetail,drivinglicence,numberplate;
    ImageView imageViewUploadCar,ViewPic;
    CardView submitdriverprofile,bckbtn;
    FirebaseDatabase database;
    DatabaseReference table_driver_profiles;
    String mCarName,mIdDetail,mDrivingLicence,mNumberPlate;
    private FirebaseAuth mAuth;
    public static final int CAMERA_REQUESTCAMERA_REQUEST= 1888;
    ImageView driver;
    ImageView car;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);

        database = FirebaseDatabase.getInstance();
        table_driver_profiles = database.getReference("DriverProfiles"); //this your table name
        carname = findViewById(R.id.carname);
        iddetail = findViewById(R.id.iddetail);
        drivinglicence = findViewById(R.id.drivinglicence);
        numberplate = findViewById(R.id.numberplate);
        submitdriverprofile = findViewById(R.id.submitdriverprofile);
        bckbtn = findViewById(R.id.bckbtn);


        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DriverProfile.this,ChooseActivity.class);
                startActivity(intent);
                finish();
            }
        });


        submitdriverprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(carname.getText().toString())){
                    Toast.makeText(DriverProfile.this, "Please Enter Car Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(iddetail.getText().toString())){
                    Toast.makeText(DriverProfile.this, "Please Enter Id or Passport Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(drivinglicence.getText().toString())){
                    Toast.makeText(DriverProfile.this, "Please insert Driving License Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(numberplate.getText().toString())){
                    Toast.makeText(DriverProfile.this, "Please insert Number Plate! ", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog progressDialog = new ProgressDialog(DriverProfile.this);
                progressDialog.setMessage("please wait ...");
                progressDialog.show();

                mCarName= carname.getText().toString();
                mIdDetail = iddetail.getText().toString();
                mDrivingLicence = drivinglicence.getText().toString();
                mNumberPlate = numberplate.getText().toString();
                final DatabaseReference newPost = table_driver_profiles.push();

                table_driver_profiles.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        newPost.child("Car Name").setValue(mCarName);
                        newPost.child("Id Or Passport Number").setValue(mIdDetail);
                        newPost.child("Driving Licence").setValue(mDrivingLicence);
                        newPost.child("Number Plate").setValue(mNumberPlate).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    Intent mainActivity = new Intent(DriverProfile.this,OfferRide.class);
                                    startActivity(mainActivity);
                                }

                            }
                        });




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });





        driver = findViewById(R.id.ViewPic);
        car = findViewById(R.id.imageViewUploadCar);

        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // create Intent to take a picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // create Intent to take a picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);

                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 3);
            }
        });









    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    driver.setImageURI(selectedImage);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    driver.setImageURI(selectedImage);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    car.setImageURI(selectedImage);
                }
                break;
            case 3:
                if (resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    car.setImageURI(selectedImage);
                }

        }


    }

}