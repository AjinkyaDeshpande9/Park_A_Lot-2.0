package com.example.park_a_lot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    private Button Submit;
    EditText regName,regEmail,regPassword,regVecnumber, regConpassword;
    String regVectype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Submit = findViewById(R.id.regSubmitbutton);
        regName = findViewById(R.id.regName);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regVecnumber = findViewById(R.id.regVecNumber);
        regConpassword= findViewById(R.id.regConPassword);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Getting all user values to prepare them for database entry
                Intent i = getIntent();
                String UserPhone = i.getStringExtra("UserPhone");
                String userName = regName.getText().toString();
                String userEmail = regEmail.getText().toString();
                String userPassword = regPassword.getText().toString();
                String userConfirmpassword = regConpassword.getText().toString();
                String userVecnumber = regVecnumber.getText().toString();


                //String Validating Conditions
                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword) || TextUtils.isEmpty(userVecnumber) || TextUtils.isEmpty(userConfirmpassword))
                {
                    Toast.makeText(Registration.this,"All Fields are Required", Toast.LENGTH_SHORT).show();
                    regName.setError("Name is Required.");
                    regEmail.setError("Email is Required.");
                    regPassword.setError("Password is Required.");
                    regConpassword.setError("Password is Required.");
                    regVecnumber.setError("Vehicle Number is Required.");
                }
                else
                {
                    if(userPassword.equals(userConfirmpassword)){
                        System.out.println("User phone number is:"+UserPhone);
                        System.out.println("User entered name is:"+userName);
                        System.out.println("User entered email is:"+userEmail);
                        System.out.println("User entered password is:"+userPassword);
                        System.out.println("User entered vecnumber is:"+userVecnumber);
                        startActivity(new Intent(Registration.this,dummy.class));
                        //progressBar.setVisibility(View.VISIBLE);
                    }
                    else{
                        regConpassword.setError("Password does not match.");
                        Toast.makeText(Registration.this,"Password does not match!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}