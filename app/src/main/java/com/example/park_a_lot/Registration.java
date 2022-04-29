package com.example.park_a_lot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    private Button Submit;
    TextView Relogin;
    EditText regName,regEmail,regPassword,regVecnumber, regConpassword;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String userPhone, userName, userEmail,userPassword,userConfirmpassword,userVecnumber, userRegvectype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);
        Submit = findViewById(R.id.regSubmitbutton);
        regName = findViewById(R.id.regName);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regVecnumber = findViewById(R.id.regVecNumber);
        regConpassword= findViewById(R.id.regConPassword);
        radioGroup = findViewById(R.id.radioGroup);
        Relogin = findViewById(R.id.regloginHere);


        Relogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting all user values to prepare them for database entry
                Intent i = getIntent();
                userPhone = i.getStringExtra("UserPhone");
                userName = regName.getText().toString();
                userEmail = regEmail.getText().toString();
                userPassword = regPassword.getText().toString();
                userConfirmpassword = regConpassword.getText().toString();
                userVecnumber = regVecnumber.getText().toString();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                userRegvectype = radioButton.getText().toString();


                //String Validating Conditions
                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword) || TextUtils.isEmpty(userConfirmpassword) )
                {
                    Toast.makeText(Registration.this,"All Fields are Required", Toast.LENGTH_SHORT).show();
                    regName.setError("Name is Required.");
                    regEmail.setError("Email is Required.");
                    regPassword.setError("Password is Required.");
                    regConpassword.setError("Password is Required.");
                }

                else {
                    if (TextUtils.isEmpty(userVecnumber)) {
                        regVecnumber.setError("Vehicle Number is Required.");
                        Toast.makeText(Registration.this, "Vehicle Number Not entered!", Toast.LENGTH_LONG).show();
                    }
                    else if(userVecnumber.length()!=10){
                        regVecnumber.setError("Enter a valid number.");
                        Toast.makeText(Registration.this, "Vehicle number not correct!", Toast.LENGTH_LONG).show();
                    }

                    else if (userPassword.equals(userConfirmpassword) && userPassword.length() > 6) {
                        storeNewUsersData();
                        startActivity(new Intent(Registration.this, Login.class));
                    }
                    else {
                        if (userPassword.length() <= 6) {
                            regPassword.setError("Password must have atleast 7 characters");
                            Toast.makeText(Registration.this, "Password too short!", Toast.LENGTH_LONG).show();
                        } else {
                            regConpassword.setError("Password does not match.");
                            Toast.makeText(Registration.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                if (TextUtils.isEmpty(userVecnumber)) {
                    regVecnumber.setError("Vehicle Number is Required.");
                }
                else if(userVecnumber.length()!=10){
                    regVecnumber.setError("Enter a valid number.");
                }

            }
        });
    }
//Storing Users in Firebase
    private void storeNewUsersData() {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users");
        UserHelperClass addNewUser = new UserHelperClass(userName,userEmail,userPassword,userPhone,userVecnumber,userRegvectype);
        reference.child(userPhone).setValue(addNewUser);
        Toast.makeText(Registration.this, "\t\t\t\t\t\t\t\tRegistration Successful!\nYou can now Login with these Credentials ", Toast.LENGTH_LONG).show();
    }
}


