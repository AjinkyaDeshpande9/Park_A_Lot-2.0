package com.example.park_a_lot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Locale;

public class ParkingLot extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference =
            FirebaseDatabase.getInstance().getReferenceFromUrl("https://parkalot-b98ef-default-rtdb.firebaseio.com/");

    private DatePickerDialog datePickerDialog;
    private Button dateButton, timeButton;
    TextView Pavail, Prate,Padd,Pname;
    ImageView Pimage;
    int hour, minute;
    String ParkingLotNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        timeButton = findViewById(R.id.timeButton);
        Pavail = findViewById(R.id.VenueAvail);
        Prate = findViewById(R.id.VenueRate);
        Padd = findViewById(R.id.VenueAddress);
        Pname = findViewById(R.id.VenueName);
        Pimage = findViewById(R.id.VenueImage);

        Intent i = getIntent();
        ParkingLotNo = i.getStringExtra("ParkingLotid");
        System.out.println(ParkingLotNo);
        getData();
    }


    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    private void getData(){
    databaseReference.child("Venue").addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            switch(ParkingLotNo) {
                case "0001":
                    Pimage.setImageResource(R.drawable.slideshowone);
                    break;
                case "0002":
                    Pimage.setImageResource(R.drawable.slideshowtwo);
                    break;
                case "0003":
                    Pimage.setImageResource(R.drawable.slideshowthree);
                    break;
                case "0004":
                    Pimage.setImageResource(R.drawable.slideshowfour);
                    break;
                 default:
                     Pimage.setImageResource(R.drawable.slideshowfive);

            }


           Long getAvailableSlots = snapshot.child(ParkingLotNo).child("Vavail").getValue(Long.class);
           String getVenueAddress = snapshot.child(ParkingLotNo).child("Vadd").getValue(String.class);
           String getVenueName = snapshot.child(ParkingLotNo).child("Vname").getValue(String.class);
           Long getVenueRate = snapshot.child(ParkingLotNo).child("Vrate").getValue(Long.class);
           String getrate = Long.toString(getVenueRate);
           String getslots = Long.toString(getAvailableSlots);

           Padd.setText(getVenueAddress);
           Pname.setText(getVenueName);
           Prate.setText("₹ "+getrate + "/Hr");
           Pavail.setText(getslots + " Slots Available");


        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    });
}

}