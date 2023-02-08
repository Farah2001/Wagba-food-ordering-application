package com.example.wagbaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextInputEditText Reg_Email;
    TextInputEditText Reg_Pass;
    TextInputEditText Reg_usrName;
    TextInputEditText Reg_name;
    TextInputEditText Reg_Num;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //auth
        mAuth=FirebaseAuth.getInstance();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        Button CallHome;
        Button CallLogin;
        Button CallProfile;
        CallHome = (Button) findViewById(R.id.btn1signup);
        CallLogin = (Button) findViewById(R.id.btn2signup);
        CallProfile = (Button) findViewById(R.id.btn3signup);
         Reg_Email = findViewById(R.id.RegEmail);
         Reg_Pass = findViewById(R.id.RegPass);
         Reg_usrName = findViewById(R.id.RegUserName);
         Reg_name = findViewById(R.id.RegName);
         Reg_Num = findViewById(R.id.RegNumber);


        CallHome.setOnClickListener(view -> {

            createUser();
            saveData(); //for the ROOM db
        });
        CallLogin.setOnClickListener(view -> {
            Intent intent4 = new Intent(SignUp.this, Login.class);
            startActivity(intent4);
        });

        CallProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProfileData.class));
            }
        });
    }

    private void saveData() {
        String name_txt = Reg_name.getText().toString().trim();
        String usrname_txt = Reg_usrName.getText().toString().trim();
        String phonenum_txt = Reg_Num.getText().toString().trim();



            UserModel model = new UserModel();

            model.setName(name_txt);
            model.setUsrname(usrname_txt);
            model.setPnum(phonenum_txt);

            DatabaseClass.getDatabase(getApplicationContext()).getDao().insertAllData(model);

            Reg_name.setText("");
            Reg_usrName.setText("");
            Reg_Num.setText("");
            Toast.makeText(this, "Data Saved Successfully!", Toast.LENGTH_SHORT).show();

    }

    private void createUser(){
        String email = Reg_Email.getText().toString();
        String Password = Reg_Pass.getText().toString();
        String Pnum = Reg_Num.getText().toString();
        String Name = Reg_name.getText().toString();
        String UsrName = Reg_usrName.getText().toString();

        //if user entered empty fields.

        if(TextUtils.isEmpty(email)){
            Reg_Email.setError("Please write your email");
            Reg_Email.requestFocus();
        }else if (TextUtils.isEmpty(Password)){
            Reg_Pass.setError("Please enter a password");
            Reg_Pass.requestFocus();
        }else if (TextUtils.isEmpty(Pnum)){
            Reg_Num.setError("Please enter your mobile number");
            Reg_Num.requestFocus();
        }else if (TextUtils.isEmpty(Name)){
            Reg_name.setError("Please enter your full name");
            Reg_Num.requestFocus();
        }else if (TextUtils.isEmpty(UsrName)){
            Reg_usrName.setError("Please enter a user name");
            Reg_usrName.requestFocus();
        }else {
            //user entered all his info
            mAuth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()){ //user added successfully
                       Toast.makeText(SignUp.this, "User Registered Successfully!", Toast.LENGTH_SHORT).show();
                       Intent intent3= new Intent(SignUp.this, Home.class);
                       startActivity(intent3);
                }else {
                       Toast.makeText(SignUp.this,"Registration error: " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                   }
            }

            });
        }
    }
}