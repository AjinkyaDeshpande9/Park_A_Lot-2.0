package com.example.park_a_lot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.util.SharedPreferencesUtils;

public class BookingSummary extends AppCompatActivity {
    String SParkingCost,SParkingTime, SParkingDate,SParkingVenue,SParkingAddress,SParkingEndTime,SParkingDuration;
    TextView SVenueName,SVenueAddress,SVenueDate, SVenueDuration,SVenueTime, SVenueCost;
    Button ConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);
        Intent i = getIntent();
        SParkingTime = i.getStringExtra("ParkingTime");
        SParkingDate = i.getStringExtra("ParkingDate");
        SParkingCost = i.getStringExtra("ParkingCost");
        SParkingVenue = i.getStringExtra("ParkingVenue");
        SParkingAddress = i.getStringExtra("ParkingAddress");
        SParkingEndTime = i.getStringExtra("ParkingEndTime");
        SParkingDuration = i.getStringExtra("ParkingDuration");

        SVenueName = findViewById(R.id.ConfirmVenueName);
        SVenueAddress = findViewById(R.id.ConfirmVenueAddress);
        SVenueDate = findViewById(R.id.ConfirmDate);
        SVenueDuration = findViewById(R.id.ConfirmDuration);
        SVenueTime = findViewById(R.id.ConfirmTime);
        SVenueCost = findViewById(R.id.GrandTotal);
        ConfirmButton = findViewById(R.id.ConfirmtoBook);

        SVenueName.setText(SParkingVenue);
        SVenueAddress.setText(SParkingAddress);
        SVenueDate.setText(SParkingDate);
        SVenueDuration.setText(SParkingDuration);
        SVenueTime.setText(SParkingTime);
        SVenueCost.setText(SParkingCost);

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ConfirmedBooking.class));
            }
        });
        System.out.println("Parking cost "+SParkingCost);
//        System.out.println("Parking Date "+SParkingDate );
//        System.out.println("Parking Time "+SParkingTime);
//        System.out.println("Parking Venue "+SParkingVenue);
//        System.out.println("Parking Address "+SParkingAddress);
//        System.out.println("Parking Address "+SParkingEndTime);
    }
}