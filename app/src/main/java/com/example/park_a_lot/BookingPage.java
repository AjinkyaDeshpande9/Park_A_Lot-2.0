package com.example.park_a_lot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class BookingPage extends AppCompatActivity {
    Button parkingbookbtn1, parkingbookbtn2, parkingbookbtn3, parkingbookbtn4;
    String ParkingLotNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        parkingbookbtn1 = findViewById(R.id.parkingBookbtn1);
        parkingbookbtn2 = findViewById(R.id.parkingBookbtn2);
        parkingbookbtn3 = findViewById(R.id.parkingBookbtn3);
        parkingbookbtn4 = findViewById(R.id.parkingBookbtn4);



        parkingbookbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkingLotNo ="0001";
                Intent i = new Intent(getApplicationContext(), ParkingLot.class);
                i.putExtra("ParkingLotid",ParkingLotNo);
                startActivity(i);
            }
        });

        parkingbookbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkingLotNo ="0002";
                Intent i = new Intent(getApplicationContext(), ParkingLot.class);
                i.putExtra("ParkingLotid",ParkingLotNo);
                startActivity(i);
            }
        });

        parkingbookbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkingLotNo ="0003";
                Intent i = new Intent(getApplicationContext(), ParkingLot.class);
                i.putExtra("ParkingLotid",ParkingLotNo);
                startActivity(i);
            }
        });

        parkingbookbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkingLotNo ="0004";
                Intent i = new Intent(getApplicationContext(), ParkingLot.class);
                i.putExtra("ParkingLotid",ParkingLotNo);
                startActivity(i);
            }
        });
    }

}