package com.example.wagbaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextInputEditText Login_mail;
    TextInputEditText Login_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        Button callSignUp = findViewById(R.id.btn2login);
        Button callHome = findViewById(R.id.btn1login);
        //auth
        mAuth=FirebaseAuth.getInstance();
        Login_mail = findViewById(R.id.LoginMail);
        Login_pass = findViewById(R.id.LoginPass);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
        callHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginUser();
            }
        });
    }


    private void LoginUser() {
        String email = Login_mail.getText().toString();
        String Password = Login_pass.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Login_mail.setError("Please write your email");
            Login_mail.requestFocus();
        } else if (TextUtils.isEmpty(Password)) {
            Login_pass.setError("Please enter a password");
            Login_pass.requestFocus();
        }else {
            mAuth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "User logged in Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(Login.this, Home.class);
                        startActivity(intent2);
                    }else {
                        Toast.makeText(Login.this, "Login error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}