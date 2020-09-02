package com.example.games.Controller.Fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.games.Model.Player;
import com.example.games.R;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Field;

public class TicTacToeFragment extends Fragment {

    public static final String EXTRA_BUTTON_COLORS = "com.example.games.Controller.Button colors";
    public static final String EXTRA_ENABLE_STATE = "com.example.games.Controller.Enable state";
    private TextView mPlayerOneName, mPlayerTwoName;
    private Button mBtnRefresh;
    private Button[] mButtons = new Button[9];
    private ViewGroup mBtnsLay;
    private Player mPlayerOne = new Player(),mPlayerTwo = new Player();
    private int[] mResId = createId(mButtons, "img_");
    private int mCounter = 0;

    // for save Instance
    private int[] mDrawables=new int[9];
    private boolean[] mEnableState=new boolean[9];

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
        setInstanceState(savedInstanceState);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        for (int i = 0; i <mButtons.length ; i++) {
            mDrawables=mButtons[i].getDrawableState();
            if(mButtons[i].getSolidColor()!=getResources().getColor(R.color.yellow_green))
                mEnableState[i]=false;
        }

        outState.putIntArray(EXTRA_BUTTON_COLORS,mDrawables);
        outState.putBooleanArray(EXTRA_ENABLE_STATE,mEnableState);
    }

    private void setInstanceState(Bundle bundle){
        if(bundle!=null){
           int[] colors= bundle.getIntArray(EXTRA_BUTTON_COLORS);
            for (int i = 0; i < colors.length; i++) {
                mButtons[i].setBackground(getResources().getDrawable(colors[i]));
            }
        }

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

        mPlayerOneName = view.findViewById(R.id.player_one_name);
        mPlayerTwoName = view.findViewById(R.id.player_two_name);

        mBtnRefresh = view.findViewById(R.id.btn_refresh);

        mBtnsLay = view.findViewById(R.id.game_btns);
    }

    private void setListeners() {
        for (int i = 0; i < mButtons.length; i++) {

            final int finalI = i;
            mButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCounter++;
                    if (mCounter % 2 == 0) {
                        mButtons[finalI].setBackground(mPlayerOne.getImage());

                    } else {
                        mButtons[finalI].setBackground(mPlayerTwo.getImage());
                    }
                    mButtons[finalI].setEnabled(false);

                    if (mCounter == mButtons.length) {
                        checkWinner();
                        mBtnRefresh.setVisibility(View.VISIBLE);
                        mBtnsLay.setVisibility(View.INVISIBLE);
                    }

                }

            });
            mBtnRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshGame();
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
        if (calculateScore(mPlayerOne) > calculateScore(mPlayerTwo)) {
            returnSnackbar("Horaaaaaa " + mPlayerOne.getUserName() + " is Winner");

            mPlayerOneName.setText(mPlayerOneName.getText() + "   Winner!!");
            mPlayerOneName.setTextColor(getResources().getColor(R.color.red));
        } else if (calculateScore(mPlayerOne) < calculateScore(mPlayerTwo)) {
            returnSnackbar("Horaaaaaa " + mPlayerTwo.getUserName() + " is Winner");

            mPlayerTwoName.setText(mPlayerTwoName.getText() + "   Winner!!");
            mPlayerTwoName.setTextColor(getResources().getColor(R.color.red));
        } else {
            returnSnackbar(mPlayerOne.getUserName() + " and " + mPlayerTwo.getUserName() + " are Winner");

            mPlayerOneName.setText(mPlayerOneName.getText() + "   Winner!!");
            mPlayerTwoName.setText(mPlayerTwoName.getText() + "   Winner!!");
            mPlayerOneName.setTextColor(getResources().getColor(R.color.red));
            mPlayerTwoName.setTextColor(getResources().getColor(R.color.red));
        }
    }

    private int calculateScore(Player player) {
        int score = 0;
        if (inRow(mButtons[0].getBackground(), mButtons[1].getBackground(), mButtons[2].getBackground(), player.getImage())) {
            score++;
        } else if (inRow(mButtons[3].getBackground(), mButtons[4].getBackground(), mButtons[5].getBackground(), player.getImage())) {
            score++;
        } else if (inRow(mButtons[6].getBackground(), mButtons[7].getBackground(), mButtons[8].getBackground(), player.getImage())) {
            score++;
        } else if (inRow(mButtons[2].getBackground(), mButtons[4].getBackground(), mButtons[6].getBackground(), player.getImage())) {
            score++;
        } else if (inRow(mButtons[0].getBackground(), mButtons[4].getBackground(), mButtons[8].getBackground(), player.getImage())) {
            score++;
        } else if (inRow(mButtons[0].getBackground(), mButtons[3].getBackground(), mButtons[6].getBackground(), player.getImage())) {
            score++;
        } else if (inRow(mButtons[2].getBackground(), mButtons[5].getBackground(), mButtons[8].getBackground(), player.getImage())) {
            score++;
        } else if (inRow(mButtons[1].getBackground(), mButtons[4].getBackground(), mButtons[7].getBackground(), player.getImage())) {
            score++;
        }
        return score;
    }

    private boolean inRow(Drawable one, Drawable two, Drawable three, Drawable designId) {
        if (one == designId && two == designId && three == designId)
            return true;

        return false;
    }

    private void refreshGame() {
        for (int i = 0; i < mButtons.length; i++) {
            mButtons[i].setBackgroundColor(getResources().getColor(R.color.yellow_green));
            mButtons[i].setEnabled(true);
        }

        mCounter = 0;
        mPlayerOne.setScore(0);
        mPlayerTwo.setScore(0);

        mPlayerOneName.setText(mPlayerOne.getUserName() + "");
        mPlayerTwoName.setText(mPlayerTwo.getUserName() + "");

        mPlayerOneName.setTextColor(Color.BLACK);
        mPlayerTwoName.setTextColor(Color.BLACK);

        mBtnRefresh.setVisibility(View.GONE);
        mBtnsLay.setVisibility(View.VISIBLE);
    }

    public void returnSnackbar(String msg) {
        Snackbar snackbar = Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}