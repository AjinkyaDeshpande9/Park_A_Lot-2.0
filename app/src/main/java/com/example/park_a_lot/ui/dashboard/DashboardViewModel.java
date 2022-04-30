package com.example.park_a_lot.ui.dashboard;

import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {
    TextView PName,PEmail,PMobile,PVectype, PVecNumber;
    EditText Ppassword;

    private final MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}