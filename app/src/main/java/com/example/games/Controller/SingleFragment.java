package com.example.games.Controller;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.games.R;

public abstract class SingleFragment extends AppCompatActivity {

    public abstract Fragment getFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragmentTest=fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragmentTest == null) {
            fragmentManager.beginTransaction().add(R.id.fragment_container, getFragment()).commit();
        }

    }
}
