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

public class GamesActivity extends AppCompatActivity {
    FragmentManager mFragmentManager = getSupportFragmentManager();
    //GamesFragment mGamesFragment=new GamesFragment();
    TicTacToeFragment mTicTacToeFragment = new TicTacToeFragment();
    FourInRowFragment mFourInRowFragment = new FourInRowFragment();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    Fragment fragment;
    public Button mBtnTicTacToe, mBtnFourInRow;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findElem();
        setListener();
        setFragment(R.id.game_container,mTicTacToeFragment);
        setFragment(R.id.game_container,mFourInRowFragment);
    }


    public void findElem() {
        mBtnTicTacToe = findViewById(R.id.btn_ticetactoe);
        mBtnFourInRow = findViewById(R.id.btn_four_in_row);
    }

    public void setListener() {
        mBtnFourInRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(mFourInRowFragment, mTicTacToeFragment);
            }
        });

        mBtnTicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(mTicTacToeFragment,mFourInRowFragment);
            }
        });
    }

    private void showFragment(Fragment fragmentShow,Fragment fragmentInShow) {
        fragmentInShow.getView().setVisibility(View.INVISIBLE);
        fragmentShow.getView().setVisibility(View.VISIBLE);
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