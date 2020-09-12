package com.example.games.Controller.Activity;

import androidx.fragment.app.Fragment;

import com.example.games.Controller.Fragment.SettingFragment;
import com.example.games.Controller.SingleFragment;
import com.example.games.R;

public class SettingActivity extends SingleFragment {

    @Override
    public Fragment getFragment() {
        return new SettingFragment();
    }

    @Override
    public int getContainer() {
        return R.id.setting_frag_container;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

}