package com.example.games.Controller.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.games.R;

public abstract class SingleFragment extends AppCompatActivity {

    public abstract Fragment getFragment();
    public abstract  int getContainer();
    public abstract int getLayout();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragmentTest=fragmentManager.findFragmentById(getContainer());
        if (fragmentTest == null) {
            fragmentManager.beginTransaction().add(getContainer(), getFragment()).commit();
        }
    }
}
