package com.example.games.Controller.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.games.Controller.Activity.GamesActivity;
import com.example.games.Model.Player;
import com.example.games.R;
import com.example.games.SimpleViewModel;

public class UserLoginFragment extends Fragment {
    public static final String EXTRA_PLAYER_ONE_USERNAME = "com.example.games.Controller.Fragment.Player One User Name";
    public static final String EXTRA_PLAYER_TWO_USERNAME = "com.example.games.Controller.Fragment.Player Two User Name";
    public static final String BUNDLE_PLAYER_ONE_USERNAME = "Player One User Name";
    public static final String BUNDLE_PLAYER_TWO_USERNAME = "Player Two User Name";
    private Player mPlayerOne = new Player();
    private Player mPlayerTwo = new Player();
    String mNameOne,mNameTwo="hohoho";

    private EditText mNamePlayerOne, mNamePlayerTwo;
    private com.varunest.sparkbutton.SparkButton mBtnGo;

    public UserLoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
        findElem(view);
        setListener();
        if(savedInstanceState!=null)
            saveInstance(savedInstanceState);
        //mViewModel= ViewModelProvider.of(getActivity()).get(SimpleViewModel.class);
        return view;
    }


    public void findElem(View view) {
        mNamePlayerOne = view.findViewById(R.id.player_two);
        mNamePlayerTwo = view.findViewById(R.id.player_one);
        mBtnGo = view.findViewById(R.id.btn_go);
    }

    public void setListener() {
        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPlayer(mNamePlayerOne.getText().toString()) &&
                        checkPlayer(mNamePlayerTwo.getText().toString())) {
                    setPlayers(mPlayerOne, mNamePlayerOne.getText().toString());
                    setPlayers(mPlayerTwo, mNamePlayerTwo.getText().toString());
                    Intent intent = new Intent(getActivity(), GamesActivity.class);
                    intent.putExtra(EXTRA_PLAYER_ONE_USERNAME, mPlayerOne.getUserName());
                    intent.putExtra(EXTRA_PLAYER_TWO_USERNAME, mPlayerTwo.getUserName());
                    startActivity(intent);
                } else
                    returnToast(R.string.toast_wrong);
            }
        });
    }

    private boolean checkPlayer(String str) {
        if (!isNumeric(str) && !str.equals("")) {
            return true;
        }
        returnToast(R.string.toast_username_players);
        return false;
    }

    private void setPlayers(Player player, String userName) {
        player.setUserName(userName);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getActivity();
        outState.putString(BUNDLE_PLAYER_ONE_USERNAME,mNamePlayerOne.getText().toString());
        outState.putString(BUNDLE_PLAYER_TWO_USERNAME,mNamePlayerTwo.getText().toString());
    }

    public void saveInstance(Bundle bundle){
        if(bundle!=null){
            mNameOne=bundle.getString(BUNDLE_PLAYER_ONE_USERNAME);
            mNamePlayerOne.setText(mNameOne);
            mNameTwo=bundle.getString(BUNDLE_PLAYER_TWO_USERNAME);
            mNamePlayerTwo.setText(mNameTwo);
        }
    }

    private void returnToast(int strId) {
        Toast.makeText(getActivity(), strId, Toast.LENGTH_LONG).show();
    }

    private static boolean isNumeric(String strNum) {
        for (char c : strNum.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

}