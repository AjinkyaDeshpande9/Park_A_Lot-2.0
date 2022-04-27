package com.example.park_a_lot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookingPage extends AppCompatActivity {
    Button parkingbookbtn1, parkingbookbtn2, parkingbookbtn3, parkingbookbtn4;

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
                startActivity(new Intent(getApplicationContext(), ParkingLot1.class));
            }
        });

        parkingbookbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ParkingLot2.class));
            }
        });

        parkingbookbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ParkingLot3.class));
            }
        });

        parkingbookbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ParkingLot4.class));
            }
        });
    }

}