package com.example.games.Controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.games.Model.Player;
import com.example.games.R;

public class GamesFragment extends Fragment {

    public GamesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public Button mBtnTicTacToe,mButFourInRow;
    boolean mTicTacToeSelect,mFourInRowSelect;
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
        mButFourInRow=view.findViewById(R.id.btn_four_in_row);
    }

    public void setListener(){
            mButFourInRow.setOnClickListener(new View.OnClickListener() {
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