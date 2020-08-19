package com.example.games.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.games.R;

public class GamesActivity extends AppCompatActivity {
    FragmentManager mFragmentManager=getSupportFragmentManager();
    GamesFragment mGamesFragment=new GamesFragment();
    UserLoginFragment mUserLoginFragment=new UserLoginFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            if(mUserLoginFragment!=null)
                mFragmentManager.beginTransaction().add(R.id.fragment_container,mGamesFragment).commit();
    }
}