package com.example.park_a_lot;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.park_a_lot.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.park_a_lot.databinding.*;
import com.google.firebase.auth.FirebaseAuth;

public class NavBar extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavBarBinding binding;
    String ProfilePhone, ProfileName, ProfileEmail,ProfilePassword,ProfileVecnumber, Profilevectype;
    TextView PName,PEmail,PMobile,PVectype, PVecNumber;
    EditText Ppassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        showalluserdata();
        binding = ActivityNavBarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PName = findViewById(R.id.NameDash);
        PEmail = findViewById(R.id.EmailDash);
        PMobile = findViewById(R.id.MobileNumberDash);
        PVectype = findViewById(R.id.NameDash);
        PVecNumber = findViewById(R.id.NameDash);

        setSupportActionBar(binding.appBarNavBar.toolbar);
        binding.appBarNavBar.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "I have Issues/ Suggestions"+ "&body=" + "**Thank you for contacting Park A Lot.\n Please reference your Mobile Number while writing your query.\n We will get back to you shortly! " + "&to=" + "parkalot4@gmail.com");
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Contact Us"));
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_dashboard, R.id.nav_aboutus)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_bar);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void showalluserdata() {
        Intent i = getIntent();
        ProfilePhone = i.getStringExtra("ProfilePhone");
        ProfileEmail =  i.getStringExtra("ProfileEmail");
        ProfileName =  i.getStringExtra("ProfileName");
        ProfileVecnumber =  i.getStringExtra("ProfileVecnumber");
        Profilevectype =  i.getStringExtra("ProfileVectype");
        ProfilePassword =  i.getStringExtra("ProfilePassword");


// set Fragmentclass Arguments
        System.out.println(ProfileEmail);
        System.out.println(ProfilePhone);
        System.out.println(ProfileName);
        System.out.println(ProfileVecnumber);
        System.out.println(Profilevectype);
        System.out.println(ProfilePassword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_bar);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public String GetUserId(){
        return ProfilePhone;
    }

    public  String GetEmailId(){
        return ProfileEmail;
    }
    public  String VehicleNumber(){
        return ProfileVecnumber;
    }
    public  String GetUserName(){
        return ProfileName;
    }
    public  String GetVehicleType(){
        return Profilevectype;
    }

}

