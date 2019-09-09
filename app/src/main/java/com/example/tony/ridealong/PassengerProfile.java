package com.example.tony.ridealong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PassengerProfile extends AppCompatActivity {

    EditText iddetail;
    ImageView addcustomerimage;
    CardView submit;
    FirebaseDatabase database;
    DatabaseReference table_passenger_profiles;
    String mIdDetail;
    private Uri uri = null;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;
    public static final int CAMERA_REQUESTCAMERA_REQUEST= 1888;
    ImageView passenger;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_profile);


        database = FirebaseDatabase.getInstance();
        table_passenger_profiles = database.getReference("PassengerProfiles"); //this your table name
        iddetail = findViewById(R.id.iddetail);
        submit = findViewById(R.id.submit);
        storageReference = FirebaseStorage.getInstance().getReference();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String mIdDetail = iddetail.getText().toString().trim();

                if (TextUtils.isEmpty(mIdDetail)){
                    Toast.makeText(PassengerProfile.this, "Please Enter Id or Passport Number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog progressDialog = new ProgressDialog(PassengerProfile.this);
                progressDialog.setMessage("please wait ...");
                progressDialog.show();



                table_passenger_profiles.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        com.example.tony.ridealong.Model.passengerProfile passengerProfile = new com.example.tony.ridealong.Model.passengerProfile(mIdDetail);
                        table_passenger_profiles.child(mIdDetail).setValue(passengerProfile);

                        Toast.makeText(PassengerProfile.this, "Welcome", Toast.LENGTH_SHORT).show();
                        final Intent mainIntent = new Intent(PassengerProfile.this, SearchDestination.class);
                        PassengerProfile.this.startActivity(mainIntent);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        PassengerProfile.this.finish();



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });





        passenger = findViewById(R.id.addcustomerimage);


        passenger.setOnClickListener(new View.OnClickListener() {
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





    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    passenger.setImageURI(selectedImage);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    passenger.setImageURI(selectedImage);
                }


        }


    }


}


