package com.example.tony.ridealong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class PassengerRegister extends AppCompatActivity {

    private EditText mEmail, mPassword, mPassword2;
    private TextView mAlreadyregisterd;
    private CardView mSubmit;
    private ProgressDialog dialog;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_register);

        mSubmit = (CardView) findViewById(R.id.submit);
        mAlreadyregisterd = (TextView) findViewById(R.id.alreg);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mPassword2 = (EditText) findViewById(R.id.password2);
        dialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Passengers");

        mAlreadyregisterd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), PassengerLoginActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

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

                //progressBar.setVisibility(View.VISIBLE);
                //create user
                dialog.setMessage("Signing Up");
                dialog.show();

                mAuth.createUserWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(PassengerRegister.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    String user_id = mAuth.getCurrentUser().getUid();
                                    DatabaseReference current_user_db = mDatabase.child(user_id);
                                    current_user_db.child("email").setValue(email1);
                                    current_user_db.child("password").setValue(password1);

                                    Toast.makeText(PassengerRegister.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    final Intent mainIntent = new Intent(PassengerRegister.this, PassengerProfile.class);
                                    PassengerRegister.this.startActivity(mainIntent);
                                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    PassengerRegister.this.finish();

                                }else{
                                    Toast.makeText(PassengerRegister.this, "Error in Signing Up. Please try again later....", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }

                            }
                        });
            }
        });
    }
}
