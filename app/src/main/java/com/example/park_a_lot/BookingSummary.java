package com.example.park_a_lot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookingSummary extends AppCompatActivity {
    String SParkingCost,SParkingTime, SParkingDate,SParkingVenue,SParkingAddress,SParkingEndTime,SParkingDuration, RawRate;
    TextView SVenueName,SVenueAddress,SVenueDate, SVenueDuration,SVenueTime, SVenueCost;
    Button ConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);
        Intent i = getIntent();
        SParkingTime     = i.getStringExtra    ("ParkingTime");
        SParkingDate     = i.getStringExtra    ("ParkingDate");
        SParkingCost     = i.getStringExtra    ("ParkingCost");
        SParkingVenue    = i.getStringExtra   ("ParkingVenue");
        SParkingAddress  = i.getStringExtra ("ParkingAddress");
        SParkingEndTime  = i.getStringExtra ("ParkingEndTime");
        SParkingDuration = i.getStringExtra("ParkingDuration");
        RawRate          = i.getStringExtra("ParkingRawRate");

        SVenueName = findViewById(R.id.SummaryVenueName);
        SVenueAddress = findViewById(R.id.SummaryVenueAddress);
        SVenueDate = findViewById(R.id.SummaryDate);
        SVenueDuration = findViewById(R.id.SummaryDuration);
        SVenueTime = findViewById(R.id.SummaryTime);
        SVenueCost = findViewById(R.id.SummaryGrandTotal);
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

                Intent i2 = new Intent(getApplicationContext(), ConfirmedBooking.class);
                i2.putExtra("ParkingTime2",SParkingTime);
                i2.putExtra("ParkingDate2",SParkingDate);
                i2.putExtra("ParkingCost2", SParkingCost );
                i2.putExtra("ParkingVenue2",SParkingVenue);
                i2.putExtra("ParkingAddress2",SParkingAddress);
                i2.putExtra("ParkingEndTime2",SParkingEndTime);
                i2.putExtra("ParkingDuration2",SParkingDuration);
                i2.putExtra("RawRate2", RawRate);
                startActivity(i2);
            }
        });
        System.out.println("Parking cost "+SParkingCost);

    }
}