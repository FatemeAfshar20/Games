package com.example.games.Controller.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.games.Controller.Fragment.FourInRowFragment;
import com.example.games.Controller.Fragment.TicTacToeFragment;
import com.example.games.R;
import com.google.android.material.button.MaterialButton;

public class GamesActivity extends AppCompatActivity {
    FragmentManager mFragmentManager = getSupportFragmentManager();
    TicTacToeFragment mTicTacToeFragment = new TicTacToeFragment();

    private Button mBtnTicTacToe, mBtnFourInRow;
    private MaterialButton mBtnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findElem();
        setListener();
        setFragment(R.id.fragment_container,mTicTacToeFragment);
    }

    public void findElem() {
        mBtnTicTacToe = findViewById(R.id.btn_ticetactoe);
        mBtnFourInRow = findViewById(R.id.btn_four_in_row);
        mBtnSetting=findViewById(R.id.btn_setting);
    }

    public void setListener() {
        mBtnFourInRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager.beginTransaction().replace(R.id.fragment_container,new FourInRowFragment()).commit();
            }
        });

        mBtnTicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager.beginTransaction().replace(R.id.fragment_container,new TicTacToeFragment()).commit();
            }
        });

        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GamesActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setFragment(int idContainer, Fragment fragment) {
        Fragment   fragmentTest=mFragmentManager.findFragmentById(idContainer);
        if (fragmentTest == null) {
            mFragmentManager.beginTransaction().add(idContainer, fragment).commit();
        }
    }

/*    public void setFragmentGame(){
        if(mGamesFragment.mTicTacToeSelect){
            TicTacToeFragment mTicTacToeFragment=new TicTacToeFragment();
                mFragmentManager.beginTransaction().add(R.id.game_container,mTicTacToeFragment);
        }else if(mGamesFragment.mFourInRowSelect){
            FourInRowFragment mFourInRowFragment=new FourInRowFragment();
                mFragmentManager.beginTransaction().add(R.id.game_container,mFourInRowFragment);
        }
    }*/
}