package com.example.games.Controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.games.Model.Player;
import com.example.games.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.InputMismatchException;

public class UserLoginFragment extends Fragment {
    public static final String EXTRA_PLAYER_ONE_USERNAME = "Player One User Name";
    public static final String EXTRA_PLAYER_TWO_USERNAME = "Player Two User Name";
    private Player mPlayerOne=new Player();
    private Player mPlayerTwo=new Player();

    private com.google.android.material.textfield.TextInputLayout mNamePlayerOne,mNamePlayerTwo;
    private Button mBtnGo;

    public UserLoginFragment(){
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
        View view=inflater.inflate(R.layout.fragment_user_login, container, false);
        findElem(view);
        setListener();
        return view;
    }

    public void findElem(View view){
        mNamePlayerOne= view.findViewById(R.id.player_one);
        mNamePlayerTwo=view.findViewById(R.id.player_two);
        mBtnGo=view.findViewById(R.id.btn_go);
    }

    public void setListener(){
        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAndSetPlayers()){
                    Intent intent=new Intent(getActivity(),GamesActivity.class);
                    intent.putExtra(EXTRA_PLAYER_ONE_USERNAME,mPlayerOne.getUserName());
                    intent.putExtra(EXTRA_PLAYER_TWO_USERNAME,mPlayerTwo.getUserName());
                    startActivity(intent);
                }else
                    returnToast(R.string.toast_wrong);
            }
        });
    }

    private boolean checkAndSetPlayers() {
        try{
            mPlayerOne.setUserName(mNamePlayerOne.getEditText().getText().toString());
            mPlayerTwo.setUserName(mNamePlayerOne.getEditText().getText().toString());
        }catch (Exception e){
            returnToast(R.string.toast_username_players);
            return false;
        }
        return true;
    }

    private void returnToast(int strId) {
        Toast.makeText(getActivity(),strId,Toast.LENGTH_LONG).show();
    }
}