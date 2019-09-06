package com.example.tony.ridealong;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.PointerIconCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.GoogleAuthProvider;



public class PassengerLoginActivity extends AppCompatActivity {

    private SignInButton mGoogleBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final int RC_SIGN_IN=1;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG ="LoginActivity";
    private Button button;
    CardView mLogin;
    private EditText mEmail,mPass;
    private TextView register;
    private ProgressDialog dialog;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_passenger_login);

        mAuth = FirebaseAuth.getInstance();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        register=(TextView) findViewById(R.id.register);
        mEmail=(EditText) findViewById(R.id.email);
        mPass=(EditText)findViewById(R.id.password);
        mLogin=(CardView) findViewById(R.id.login);
        dialog=new ProgressDialog(this);
        mGoogleBtn=(SignInButton)findViewById(R.id.googlebtn);

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(PassengerLoginActivity.this, PassengerProfile.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), PassengerRegister.class);
                startActivityForResult(myIntent, 0);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString().trim();
                final String password = mPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(PassengerLoginActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(PassengerLoginActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(PassengerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(PassengerLoginActivity.this, "Signin Error", Toast.LENGTH_SHORT).show();
                        } else {
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Passengers").child(user_id);
                            current_user_db.setValue(true);
                        }
                    }
                });
            }
        });
    }

    public void forgotpassword(View view){
        Intent intent = new Intent(PassengerLoginActivity.this, ForgotPass.class);
        startActivity(intent);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                dialog.dismiss();
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            // FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            // Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            dialog.dismiss();
                            Toast.makeText(PassengerLoginActivity.this,"Make sure you Have Internet Connection",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    private void StartSignin() {
        dialog.setMessage("Signing in");
        dialog.show();

        String email1 = mEmail.getText().toString().trim();
        String password = mPass.getText().toString().trim();
        if(TextUtils.isEmpty(email1)||TextUtils.isEmpty(password)){
            Toast.makeText(PassengerLoginActivity.this,"enter all the fields",Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }

    }

    public void onPermissionsGranted(int requestCode) {
        if (requestCode == PointerIconCompat.TYPE_COPY ) {
//            button_take_a_photo1.setEnabled(true);
//            button_take_a_photo2.setEnabled(true);
        }
    }

    public void onPermissionsDenied(int requestCode) {
        if (requestCode == PointerIconCompat.TYPE_COPY) {
//            button_take_a_photo1.setEnabled(false);
//            button_take_a_photo2.setEnabled(false);
        }
    }
    public void onResume() {
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
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
