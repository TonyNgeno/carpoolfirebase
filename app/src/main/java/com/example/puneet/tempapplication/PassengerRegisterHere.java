package com.example.puneet.tempapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class PassengerRegisterHere extends AppCompatActivity {

    private EditText mEmail, mPassword, mPassword2;
    private CardView mSubmit;
    private FirebaseAuth mAuth;
    private TextView mAlreadyregisterd;
    private ProgressDialog dialog;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_passenger_register_here);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        dialog = new ProgressDialog(this);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mPassword2 = (EditText) findViewById(R.id.password2);
        mSubmit = (CardView) findViewById(R.id.submit);
        mAlreadyregisterd = (TextView) findViewById(R.id.alreg);
        mAuth = FirebaseAuth.getInstance();


        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email1 = mEmail.getText().toString().trim();
                final String password1 = mPassword.getText().toString().trim();
                final String password22 = mPassword2.getText().toString().trim();

                if (TextUtils.isEmpty(email1)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password1.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Objects.equals(password1, password22)) {

                    //  Log.d(password1,password22);
                    Toast.makeText(getApplicationContext(), "Both Passwords not Matched! ", Toast.LENGTH_SHORT).show();
                }


//        progressBar.setVisibility(View.VISIBLE);
                //create user
                dialog.setMessage("Signing Up");
                dialog.show();
                mAuth.createUserWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(PassengerRegisterHere.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(PassengerRegisterHere.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    String user_id = mAuth.getCurrentUser().getUid();
                                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Passengers").child(user_id);
                                    current_user_db.setValue(true);
                                }
                            }
                        });




            }

        });

        mAlreadyregisterd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassengerRegisterHere.this, PassengerLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


    }





    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }
}



