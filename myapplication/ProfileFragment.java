package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {


  TextView profile_tv;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profile_tv = view.findViewById(R.id.profile_tv);
        updateLanguage();
        return view;


    }


    public void updateLanguage() {
        if (profile_tv != null) {
            profile_tv.setText(MainActivity.isGeorgian ? "პროფილის ფრაგმენტი" : "profile fragment");
        }
    }

}