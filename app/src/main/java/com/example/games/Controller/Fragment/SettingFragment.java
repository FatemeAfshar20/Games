package com.example.games.Controller.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.games.Controller.Activity.UserLoginActivity;
import com.example.games.R;
import com.google.android.material.button.MaterialButton;

public class SettingFragment extends Fragment {
    private MaterialButton mBtnLogout;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_setting, container, false);
        findElem(view);
        setListener();
        return view;
    }

    private void findElem(View view){
        mBtnLogout=view.findViewById(R.id.btn_logout);
    }

    private void setListener(){
        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), UserLoginActivity.class);
            }
        });
    }
}