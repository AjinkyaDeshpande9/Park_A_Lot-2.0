package com.example.park_a_lot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class BookingSummary extends AppCompatActivity {
    String SParkingCost,SParkingTime, SParkingDate,SParkingVenue,SParkingAddress,SParkingEndTime,SParkingDuration, RawRate, ParkingLotNo,getslots,Rand, Userid,EmailId;
    TextView SVenueName,SVenueAddress,SVenueDate, SVenueDuration,SVenueTime, SVenueCost;
    Button ConfirmButton;
    DatabaseReference databaseReference =
            FirebaseDatabase.getInstance().getReferenceFromUrl("https://parkalot-b98ef-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
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
        ParkingLotNo     = i.getStringExtra("ParkingLotNo");
        getslots         =i.getStringExtra("AvailableSlots");
        Userid           =i.getStringExtra("UserId4");
        EmailId          =i.getStringExtra("EmailId4");
        System.out.println("this is emai id in booking summary"+EmailId);

        System.out.println("This is user id:"+ Userid);


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
        SVenueCost.setText("₹ " +SParkingCost);

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
                i2.putExtra("EmailId5",EmailId);
//                System.out.println("Random number in summary: "+ Rand);
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Venue");
                int plotno = (int)(Integer.valueOf(getslots))-1;
                HashMap hashMap = new HashMap();
                hashMap.put("Vavail",plotno);

                databaseReference.child(ParkingLotNo).updateChildren(hashMap);

                getBookingId();
                i2.putExtra("QRcodenumber",Rand);
                startActivity(i2);
            }
        });

    }

    private void getBookingId() {
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        Rand = String.format("%10d", number);
        storeBookingData();
    }

    private void storeBookingData() {
        databaseReference.child("Bookings").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(Rand)) {
                    getBookingId();
                }
                else{
                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                    DatabaseReference reference = rootNode.getReference("Bookings");
                    UserHelperClass1 addNewBooking = new UserHelperClass1(SParkingCost,SParkingDate,SParkingTime ,ParkingLotNo ,SParkingDuration,Userid);
                    reference.child(Rand).setValue(addNewBooking);
                    }

    }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
}
    }