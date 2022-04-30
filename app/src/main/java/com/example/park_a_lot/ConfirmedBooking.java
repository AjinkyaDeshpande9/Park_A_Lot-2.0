package com.example.park_a_lot;

import static android.graphics.Color.WHITE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfirmedBooking extends AppCompatActivity {

    Button BackToHome;
    String CParkingCost, CParkingTime, CParkingDate, CParkingVenue, CParkingAddress, CParkingEndTime, CParkingDuration, CRawRate;
    TextView CVenueName, CVenueAddress, CVenueDate, CVenueTime, CVenueCost, CVenueEndTime, CVenueEndDate, CVenueParkingRate,
            CCGst, CSGst;
    int calrate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_confirmed_booking);
        BackToHome = findViewById(R.id.BackToHome);

        Intent i = getIntent();
        CParkingCost = i.getStringExtra("ParkingCost2");
        CParkingTime = i.getStringExtra("ParkingTime2");
        CParkingDate = i.getStringExtra("ParkingDate2");
        CParkingVenue = i.getStringExtra("ParkingVenue2");
        CParkingAddress = i.getStringExtra("ParkingAddress2");
        CParkingEndTime = i.getStringExtra("ParkingEndTime2");
        CParkingDuration = i.getStringExtra("ParkingDuration2");
        CRawRate = i.getStringExtra("RawRate2");

        CVenueName = findViewById(R.id.ConfirmVenueName);
        CVenueAddress = findViewById(R.id.ConfirmVenueAddress);
        CVenueDate = findViewById(R.id.ParkingDateStart);
        CVenueTime = findViewById(R.id.ParkingTimeStart);
        CVenueCost = findViewById(R.id.GrandTotalRate);
        CVenueEndDate = findViewById(R.id.ParkingDateEnd);
        CVenueEndTime = findViewById(R.id.ParkingTimeEnd);
        CVenueParkingRate = findViewById(R.id.ParkingRate);
        CCGst = findViewById(R.id.CGSTRate);
        CSGst = findViewById(R.id.SGSTRate);


        CVenueName.setText(CParkingVenue);
        CVenueAddress.setText(CParkingAddress);
        CVenueDate.setText(CParkingDate);
        CVenueTime.setText(CParkingTime);
        CVenueCost.setText("₹ " + CParkingCost);
        CVenueEndDate.setText(CParkingDate);
        CVenueEndTime.setText(CParkingEndTime);
        CVenueParkingRate.setText(CRawRate);

        calrate = Integer.valueOf(CRawRate);
        float CGST = (float) (calrate * 0.09);
        CCGst.setText(CGST + "");
        CSGst.setText(CGST + "");


        BackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NavBar.class);
                startActivity(i);
            }
        });
    }
}