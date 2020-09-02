package com.example.games.Controller.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.games.Controller.Fragment.UserLoginFragment;
import com.example.games.R;

public class UserLoginActivity extends AppCompatActivity {
    FragmentManager mFragmentManager=getSupportFragmentManager();
    UserLoginFragment mUserLoginFragment=new UserLoginFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        if(mUserLoginFragment!=null)
            mFragmentManager.beginTransaction().add(R.id.user_login_Fragment,mUserLoginFragment).commit();
    }

}