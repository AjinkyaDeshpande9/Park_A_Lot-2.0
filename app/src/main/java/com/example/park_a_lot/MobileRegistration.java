package com.example.park_a_lot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MobileRegistration extends AppCompatActivity {
    private Button verifyOTPBtn, generateOTPBtn;
    TextView mResend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_registration);
        verifyOTPBtn = findViewById(R.id.regVerifybutton);
        generateOTPBtn = findViewById(R.id.regsendotp);
        mResend = findViewById(R.id.regResendOtp);

        generateOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOTPBtn.setVisibility(View.VISIBLE);
                mResend.setVisibility(View.VISIBLE);
            }
        });

        verifyOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MobileRegistration.this,Registration.class));
            }
        });


    }
    }
