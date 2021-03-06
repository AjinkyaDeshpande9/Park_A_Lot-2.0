package com.example.park_a_lot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class BookingPage extends AppCompatActivity {
    Button parkingbookbtn1, parkingbookbtn2, parkingbookbtn3, parkingbookbtn4;
    String ParkingLotNo, UserId, EmailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_booking_page);
        parkingbookbtn1 = findViewById(R.id.parkingBookbtn1);
        parkingbookbtn2 = findViewById(R.id.parkingBookbtn2);
        parkingbookbtn3 = findViewById(R.id.parkingBookbtn3);
        parkingbookbtn4 = findViewById(R.id.parkingBookbtn4);
        Intent i = getIntent();
        UserId     = i.getStringExtra("UserId2");
        EmailId     = i.getStringExtra("EmailId2");
        System.out.println("this is emai id in booking page "+EmailId);

        parkingbookbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkingLotNo ="0001";
                Intent i = new Intent(getApplicationContext(), ParkingLot.class);
                i.putExtra("ParkingLotid",ParkingLotNo);
                i.putExtra("UserId3",UserId);
                i.putExtra("EmailId3",EmailId);
                startActivity(i);
            }
        });

        parkingbookbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkingLotNo ="0002";
                Intent i = new Intent(getApplicationContext(), ParkingLot.class);
                i.putExtra("ParkingLotid",ParkingLotNo);
                i.putExtra("UserId3",UserId);
                i.putExtra("EmailId3",EmailId);
                startActivity(i);
            }
        });

        parkingbookbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkingLotNo ="0003";
                Intent i = new Intent(getApplicationContext(), ParkingLot.class);
                i.putExtra("ParkingLotid",ParkingLotNo);
                i.putExtra("UserId3",UserId);
                i.putExtra("EmailId3",EmailId);
                startActivity(i);
            }
        });

        parkingbookbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkingLotNo ="0004";
                Intent i = new Intent(getApplicationContext(), ParkingLot.class);
                i.putExtra("ParkingLotid",ParkingLotNo);
                i.putExtra("UserId3",UserId);
                i.putExtra("EmailId3",EmailId);
                startActivity(i);
            }
        });
    }

}