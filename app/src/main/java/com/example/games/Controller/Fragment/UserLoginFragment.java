package com.example.games.Controller.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.games.Controller.Activity.GamesActivity;
import com.example.games.R;
import com.varunest.sparkbutton.SparkButton;

public class UserLoginFragment extends Fragment {
    public static final String EXTRA_PLAYER_ONE_USERNAME = "com.example.games.Controller.Fragment.User Name One";
    public static final String EXTRA_PLAYER_TWO_USERNAME = "com.example.games.Controller.Fragment.User Name Two";
    public static final String BUNDLE_USERNAME_ONE = "UserName One";
    public static final String BUNDLE_USERNAME_TWO = "UserName Two";
    SparkButton mBtnGo;
    EditText mNameOneText,mNameTwoText;

    public UserLoginFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user_login,container,false);
        findElem(view);
        setListener();
        saveInstance(savedInstanceState);
        return view;
    }

    private void findElem(View view){
        mBtnGo=view.findViewById(R.id.btn_go);
        mNameOneText=view.findViewById(R.id.player_one);
        mNameTwoText=view.findViewById(R.id.player_two);
    }

    private void setListener(){
        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPlayer(mNameOneText.getText().toString()) &&
                        checkPlayer(mNameTwoText.getText().toString())){

                        Intent intent = new Intent(getActivity(), GamesActivity.class);
                        intent.putExtra(EXTRA_PLAYER_ONE_USERNAME, mNameOneText.getText().toString());
                        intent.putExtra(EXTRA_PLAYER_TWO_USERNAME, mNameTwoText.getText().toString());
                        startActivity(intent);
                    }else
                    returnToast(R.string.toast_wrong);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getActivity();
        outState.getString(BUNDLE_USERNAME_ONE,mNameOneText.getText().toString());
        outState.getString(BUNDLE_USERNAME_TWO,mNameTwoText.getText().toString());
    }

    public void saveInstance(Bundle bundle){
        if(bundle!=null){
            mNameOneText.setText(bundle.getString(BUNDLE_USERNAME_ONE));
            mNameTwoText.setText(bundle.getString(BUNDLE_USERNAME_TWO));
        }
    }

    private boolean checkPlayer(String str) {
        if (!isNumeric(str) && !str.equals("")) {
            return true;
        }
        returnToast(R.string.toast_username_players);
        return false;
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