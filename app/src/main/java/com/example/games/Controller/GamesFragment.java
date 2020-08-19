package com.example.games.Controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.games.R;

public class GamesFragment extends Fragment {

    public Button mBtnTicTacToe, mBtnFourInRow;
    boolean mTicTacToeSelect,mFourInRowSelect;

    public GamesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_games, container, false);
        findElem(view);
        setListener();
        return view;
    }

    public void findElem(View view){
        mBtnTicTacToe=view.findViewById(R.id.btn_ticetactoe);
        mBtnFourInRow =view.findViewById(R.id.btn_four_in_row);
    }

    public void setListener(){
            mBtnFourInRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFourInRowSelect=true;
                }
            });

            mBtnTicTacToe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTicTacToeSelect=true;
                }
            });
    }
}