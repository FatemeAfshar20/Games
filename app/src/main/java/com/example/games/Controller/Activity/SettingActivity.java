package com.example.games.Controller.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.games.Controller.Fragment.SettingFragment;
import com.example.games.Controller.Fragment.SingleFragment;
import com.example.games.R;
import com.google.android.material.button.MaterialButton;

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