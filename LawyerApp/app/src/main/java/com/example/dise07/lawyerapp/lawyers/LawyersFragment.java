package com.example.dise07.lawyerapp.lawyers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dise07.lawyerapp.R;



public class LawyersFragment extends Fragment {


    public LawyersFragment() {
        // Required empty public constructor
    }

    public static LawyersFragment newInstance() {
        return new LawyersFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lawyers, container, false);

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}
