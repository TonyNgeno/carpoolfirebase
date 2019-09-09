package com.example.tony.ridealong;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText fpemail;
    private CardView fpcardview;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_pass);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fpemail=(EditText)findViewById(R.id.fpemail);
        dialog=new ProgressDialog(this);
        fpcardview=(CardView) findViewById(R.id.fpsubmit);
        mAuth=FirebaseAuth.getInstance();

        fpcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotpass();
            }
        });


    }

    private void forgotpass() {
        dialog.setMessage("Sending Email");
        dialog.show();
        String emaill=fpemail.getText().toString().trim();
        if(!TextUtils.isEmpty(emaill)){
            mAuth.sendPasswordResetEmail(emaill)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("Forgot Email", "Email sent.");
                                Toast.makeText(ForgotPass.this,
                                        "See Email for Furthur Instruction",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(ForgotPass.this,ChooseActivity.class);
                                dialog.dismiss();
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                dialog.dismiss();
                                Toast.makeText(ForgotPass.this,
                                        "Something Went Wrong",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else{
            dialog.dismiss();
            Toast.makeText(ForgotPass.this,
                    "Please Enter all Required Details",Toast.LENGTH_LONG).show();

        }
    }
}
