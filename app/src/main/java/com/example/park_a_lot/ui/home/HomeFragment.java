package com.example.park_a_lot.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.park_a_lot.BookingPage;
import com.example.park_a_lot.NavBar;
import com.example.park_a_lot.R;
import com.example.park_a_lot.databinding.*;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    String Userid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.button;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
        NavBar activity = (NavBar) getActivity();
        String UserId = activity.GetUserId();
        String EmailId = activity.GetEmailId();
        System.out.println("this is emai id in Home frag"+EmailId);


        View view = inflater.inflate(R.layout.fragment_home,container ,false);
        Button btnOpen = (Button) view.findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), BookingPage.class);
                i.putExtra("UserId2",UserId);
                i.putExtra("EmailId2",EmailId);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}