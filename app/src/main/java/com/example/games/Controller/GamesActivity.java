package com.example.games.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;

import com.example.games.R;

import java.lang.reflect.Field;

public class GamesActivity extends AppCompatActivity {
    FragmentManager mFragmentManager = getSupportFragmentManager();
    TicTacToeFragment mTicTacToeFragment = new TicTacToeFragment();

    public Button mBtnTicTacToe, mBtnFourInRow;

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