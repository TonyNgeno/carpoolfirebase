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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.xmlpull.v1.sax2.Driver;

public class DriverProfile extends AppCompatActivity {

    EditText carname,iddetail,drivinglicence,numberplate;
    ImageView imageViewUploadCar,ViewPic;
    CardView submitdriverprofile;
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


        submitdriverprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(carname.getText().toString())){
                    Toast.makeText(DriverProfile.this, "Empty fields !!!", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(iddetail.getText().toString())){
                    Toast.makeText(DriverProfile.this, "Empty fields !!!", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(drivinglicence.getText().toString())){
                    Toast.makeText(DriverProfile.this, "Empty fields !!!", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(numberplate.getText().toString())){
                    Toast.makeText(DriverProfile.this, "Empty fields !!!", Toast.LENGTH_SHORT).show();
                }

                final ProgressDialog progressDialog = new ProgressDialog(DriverProfile.this);
                progressDialog.setMessage("please wait ...");
                progressDialog.show();

                mCarName= carname.getText().toString();
                mIdDetail = iddetail.getText().toString();
                mDrivingLicence = drivinglicence.getText().toString();
                mNumberPlate = numberplate.getText().toString();

                table_driver_profiles.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        com.example.tony.ridealong.Model.driverProfile driverProfile = new com.example.tony.ridealong.Model.driverProfile(mCarName,mIdDetail,mDrivingLicence,mNumberPlate);
                        table_driver_profiles.child(mCarName).setValue(driverProfile);

                        Toast.makeText(DriverProfile.this, "Welcome", Toast.LENGTH_SHORT).show();
                        final Intent mainIntent = new Intent(DriverProfile.this, PassengerProfile.class);
                        DriverProfile.this.startActivity(mainIntent);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        DriverProfile.this.finish();



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
