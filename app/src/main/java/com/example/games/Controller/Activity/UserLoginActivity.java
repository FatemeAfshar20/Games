package com.example.games.Controller.Activity;

import androidx.fragment.app.Fragment;

import com.example.games.Controller.SingleFragment;
import com.example.games.Controller.Fragment.UserLoginFragment;
import com.example.games.R;

public class UserLoginActivity extends SingleFragment {

    @Override
    public Fragment getFragment() {
        return new UserLoginFragment();
    }

    @Override
    public int getContainer() {
        return R.id.user_login_Fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_user_login;
    }

}