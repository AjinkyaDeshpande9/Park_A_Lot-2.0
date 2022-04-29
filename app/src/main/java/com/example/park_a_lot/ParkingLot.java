package com.example.park_a_lot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private Button dateButton, timeButton, ProceedToSummary;
    TextView Pavail, Prate,Padd,Pname;
    ImageView Pimage;
    int hour, minute, Pcost,calrate, endtimecal, RawRate;
    String ParkingLotNo,Pdate,Ptime,getVenueName,getVenueAddress,PendTime,PDuration,getrate, getRadioId,getslots;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
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
        ProceedToSummary = findViewById(R.id.ProceedtoSummary);
        radioGroup= findViewById(R.id.radioGroup2);

        Intent i = getIntent();
        ParkingLotNo = i.getStringExtra("ParkingLotid");
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
                 Pdate = makeDateString(day, month, year);
                dateButton.setText(Pdate);
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
                Ptime = String.format(Locale.getDefault(), "%02d:%02d",hour, minute);
                timeButton.setText(Ptime);
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
           getVenueAddress = snapshot.child(ParkingLotNo).child("Vadd").getValue(String.class);
           getVenueName = snapshot.child(ParkingLotNo).child("Vname").getValue(String.class);
           Long getVenueRate = snapshot.child(ParkingLotNo).child("Vrate").getValue(Long.class);
           getrate = Long.toString(getVenueRate);
           getslots = Long.toString(getAvailableSlots);
           calrate = Integer.valueOf(getrate);
           Padd.setText(getVenueAddress);
           Pname.setText(getVenueName);
           Prate.setText("₹ "+getrate + "/Hr");
           Pavail.setText(getslots + " Slots Available");
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    });
        ProceedToSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                getRadioId = radioButton.getText().toString();

                switch(getRadioId) {
                    case "1 Hour":
                        Pcost = (int) ((calrate)+ (0.18 * calrate));
                        RawRate = calrate*1;
                        PDuration ="1 Hour";
                        endtimecal = hour+1;
                        if(endtimecal >=24){
                            endtimecal = endtimecal -24;
                        }
                        PendTime= String.format(Locale.getDefault(), "%02d:%02d",endtimecal, minute);
                        break;
                    case "3 Hours":
                        Pcost = (int) (3*((calrate)+ (0.18 * calrate)));
                        RawRate = calrate*3;
                        PDuration ="3 Hours";
                        endtimecal = hour+3;
                        if(endtimecal >=24){
                            endtimecal = endtimecal -24;
                        }
                        PendTime= String.format(Locale.getDefault(), "%02d:%02d",endtimecal, minute);
                        break;
                    case "5 Hours":
                        Pcost = (int) (5*((calrate)+ (0.18 * calrate)));
                        RawRate = calrate*5;
                        PDuration ="5 Hours";
                        endtimecal = hour+5;
                        if(endtimecal >=24){
                            endtimecal = endtimecal -24;
                        }
                        PendTime= String.format(Locale.getDefault(), "%02d:%02d",endtimecal, minute);
                        break;
                    default:
                        Pcost = (int) 200;
                }
                String PStringCost = String.valueOf(Pcost);
                String StringRawRate = String.valueOf(RawRate);
                Intent i = new Intent(getApplicationContext(), BookingSummary.class);
                i.putExtra("ParkingTime",Ptime);
                i.putExtra("ParkingDate",Pdate);
                i.putExtra("ParkingCost",PStringCost);
                i.putExtra("ParkingVenue",getVenueName);
                i.putExtra("ParkingAddress",getVenueAddress);
                i.putExtra("ParkingEndTime", PendTime);
                i.putExtra("ParkingDuration", PDuration);
                i.putExtra("ParkingRawRate",StringRawRate);
                i.putExtra("ParkingLotNo",ParkingLotNo);
                i.putExtra("AvailableSlots",getslots);
                startActivity(i);
            }
        });
}
}