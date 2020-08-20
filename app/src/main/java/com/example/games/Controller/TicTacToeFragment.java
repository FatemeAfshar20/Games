package com.example.games.Controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.games.Model.ButtonState;
import com.example.games.Model.Player;
import com.example.games.R;

import java.lang.reflect.Field;

public class TicTacToeFragment extends Fragment {

    TextView mPlayerOneName, mPlayerTwoName;
    private Button[] mButtons = new Button[9];
    int[] mResId = createId(mButtons, "img_");
    Player mPlayerOne = new Player();
    Player mPlayerTwo = new Player();
    int counter = 0;

    public TicTacToeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        findElem(view);
        setListeners();
        setPlayers();
        // Inflate the layout for this fragment
        return view;
    }

    private void setPlayers() {
        // input username from intent
        mPlayerOne.setUserName(getActivity().getIntent().getStringExtra(UserLoginFragment.EXTRA_PLAYER_ONE_USERNAME));
        mPlayerTwo.setUserName(getActivity().getIntent().getStringExtra(UserLoginFragment.EXTRA_PLAYER_TWO_USERNAME));

        //set Image
        mPlayerOne.setImage(getResources().getDrawable(R.drawable.cicle_image));
        mPlayerTwo.setImage(getResources().getDrawable(R.drawable.close_image));

        // set Player Name in TextView
        mPlayerOneName.setText(mPlayerOne.getUserName() + "");
        mPlayerTwoName.setText(mPlayerTwo.getUserName() + "");
    }

    private void findElem(View view) {
        for (int i = 0; i < mButtons.length; i++) {
            mButtons[i] = view.findViewById(mResId[i]);
        }

        mPlayerOneName = view.findViewById(R.id.score_player_one);
        mPlayerTwoName = view.findViewById(R.id.score_player_two);
    }

    private void setListeners() {
        for (int i = 0; i < mButtons.length; i++) {

            final int finalI = i;
            mButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    counter++;
                    if (counter % 2 == 0) {
                        mButtons[finalI].setBackground(mPlayerOne.getImage());

                    } else {
                        mButtons[finalI].setBackground(mPlayerTwo.getImage());
                    }
                    mButtons[finalI].setEnabled(false);

                    if(counter==mButtons.length-1)
                        mPlayerOneName.setText(mPlayerOneName.getText()+"   "+setScore());
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

    private void checkWinner() {



    }

    private int setScore(){
        if (inRow(mButtons[0].getBackground(),mButtons[1].getBackground(),mButtons[2].getBackground(),mPlayerOne.getImage())) {
            mPlayerOne.setScore(mPlayerOne.getScore() + 1);
        }
        else if(inRow(mButtons[3].getBackground(),mButtons[4].getBackground(),mButtons[5].getBackground(),mPlayerOne.getImage()))
        {
            mPlayerOne.setScore(mPlayerOne.getScore() + 1);
        }else if(inRow(mButtons[6].getBackground(),mButtons[7].getBackground(),mButtons[8].getBackground(),mPlayerOne.getImage())){
            mPlayerOne.setScore(mPlayerOne.getScore()+1);
        }else if(inRow(mButtons[2].getBackground(),mButtons[4].getBackground(),mButtons[6].getBackground(),mPlayerOne.getImage())){
            mPlayerOne.setScore(mPlayerOne.getScore()+1);
        }else if(inRow(mButtons[0].getBackground(),mButtons[4].getBackground(),mButtons[8].getBackground(),mPlayerOne.getImage())){
            mPlayerOne.setScore(mPlayerOne.getScore()+1);
        }
        return mPlayerOne.getScore();
    }

    private boolean inRow(Drawable one, Drawable two, Drawable three, Drawable designId) {
        if (one == designId && two == designId && three == designId)
            return true;

        return false;
    }
}