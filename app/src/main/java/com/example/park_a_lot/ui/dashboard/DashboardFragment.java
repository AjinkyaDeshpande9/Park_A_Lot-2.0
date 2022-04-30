package com.example.park_a_lot.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.park_a_lot.MobileRegistration;
import com.example.park_a_lot.NavBar;
import com.example.park_a_lot.R;
import com.example.park_a_lot.databinding.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.Change;

import java.util.HashMap;

public class DashboardFragment extends Fragment {
    TextView PName,PEmail,PMobile,PVectype, PVecNumber;
    EditText Ppassword;
    String ChangePassword;
    Button UpdatePassword;
    DatabaseReference databaseReference =
            FirebaseDatabase.getInstance().getReferenceFromUrl("https://parkalot-b98ef-default-rtdb.firebaseio.com/");

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);


        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        NavBar activity = (NavBar) getActivity();
        String PMobileNo = activity.GetUserId();
        String PEmailId = activity.GetEmailId();
        String PUserName = activity.GetUserName();
        String PVehicleType = activity.GetVehicleType();
        String PVehicleNo = activity.VehicleNumber();
        //activity.SetProfileData();


        PName = root.findViewById(R.id.NameDash);
        PEmail = root.findViewById(R.id.EmailDash);
        PMobile = root.findViewById(R.id.MobileNumberDash);
        PVectype = root.findViewById(R.id.Vectypedash);
        PVecNumber = root.findViewById(R.id.VecNumberDash);
        UpdatePassword = root.findViewById(R.id.UpdatePassword);
        Ppassword = root.findViewById(R.id.ChangePasswordDash);

        PName.setText(PUserName);
        PEmail.setText(PEmailId);
        PMobile.setText(PMobileNo);
        PVectype.setText(PVehicleType);
        PVecNumber.setText(PVehicleNo);

        UpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePassword =Ppassword.getText().toString().trim();
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                if (TextUtils.isEmpty(ChangePassword)) {
                    Toast.makeText(getActivity(),"Password Cannot be empty!",Toast.LENGTH_SHORT).show();
                    Ppassword.setError("New Password Required!");
                }
                else if(ChangePassword.length() <= 6){
                    Toast.makeText(getActivity(),"Password Too short",Toast.LENGTH_SHORT).show();
                    Ppassword.setError("New Password Must be atleast 7 characters!");
                }
                else{
                    HashMap hashMap = new HashMap();
                    hashMap.put("upassword",ChangePassword);
                    databaseReference.child(PMobileNo).updateChildren(hashMap);
                    Toast.makeText(getActivity(),"Password Updated Successfully!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}