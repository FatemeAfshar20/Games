package com.example.games.Controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.games.Model.ButtonState;
import com.example.games.Model.Player;
import com.example.games.R;

import java.lang.reflect.Field;

public class FourInRowFragment extends Fragment {
    int mNumBtn=25;
    TextView mPlayerOneName, mPlayerTwoName;
    private Button[] mButtons=new Button[mNumBtn];
    int[] mResId=createId(mButtons,"btn_");
    Player mPlayerOne=new Player();
    Player mPlayerTwo =new Player();
    int counter=0;
    public FourInRowFragment() {
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
        View view=inflater.inflate(R.layout.fragment_four_in_row, container, false);
        findElem(view);
        setPlayers();
        setListeners();
        return view;
    }

    private void setPlayers() {
        // input username from intent
        mPlayerOne.setUserName(getActivity().getIntent().getStringExtra(UserLoginFragment.EXTRA_PLAYER_ONE_USERNAME));
        mPlayerTwo.setUserName(getActivity().getIntent().getStringExtra(UserLoginFragment.EXTRA_PLAYER_TWO_USERNAME));

        //set Image
        mPlayerOne.setColorID(R.color.blue_karboni_light);
        mPlayerTwo.setColorID(R.color.brown_light);

        // set Player Name in TextView
        mPlayerOneName.setText(mPlayerOne.getUserName()+"");
        mPlayerTwoName.setText(mPlayerTwo.getUserName()+"");
    }

    private void findElem(View view){
        for(int i=0;i<mButtons.length;i++){
            mButtons[i]=view.findViewById(mResId[i]);
        }

        mPlayerOneName =view.findViewById(R.id.score_player_one);
        mPlayerTwoName =view.findViewById(R.id.score_player_two);
    }

    private void setListeners(){
        for (int i = 0; i <mButtons.length ; i++) {

            final int finalI = i;
            mButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counter++;
                    if (counter % 2 == 0) {
                        mButtons[finalI].setBackgroundResource(mPlayerOne.getColorID());

                    } else {
                        mButtons[finalI].setBackgroundResource(mPlayerTwo.getColorID());
                    }
                    mButtons[finalI].setEnabled(false);
                }

            });

        }
    }

    private <T extends View> int[] createId(View[] views, String commonPartOfId) {
        int[] IDs = new int[views.length];
        for (int i = 0; i < views.length; i++) {
            int tempt = getId(commonPartOfId + i, R.id.class);
            IDs[i] = tempt;
        }
        return IDs;
    }

    private static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }

    public void checkWinner(){

    }
}