package com.example.games.Controller;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.games.Model.ButtonState;
import com.example.games.Model.Player;
import com.example.games.R;

import java.lang.reflect.Field;

public class FourInRowFragment extends Fragment {
    int mNumBtn = 5;
    TextView mPlayerOneName, mPlayerTwoName;
    private ImageButton mBtnGo, mBtnStart;
    private Button[][] mButtons = new Button[mNumBtn][mNumBtn];
    int[][] mResId = createId(mButtons, "btn_");
    Player mPlayerOne = new Player();
    Player mPlayerTwo = new Player();
    EditText mPlayerOneText, mPlayerTwoText;
    int counter = 0;
    int mColumnSelected;
    int index = mNumBtn;

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
        View view = inflater.inflate(R.layout.fragment_four_in_row, container, false);
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
        mPlayerOneName.setText(mPlayerOne.getUserName() + "");
        mPlayerTwoName.setText(mPlayerTwo.getUserName() + "");
    }

    private void findElem(View view) {
        for (int i = 0; i < mButtons.length; i++) {
            for (int j = 0; j < mButtons.length; j++) {
                mButtons[i][j] = view.findViewById(mResId[i][j]);
            }
        }

        mPlayerOneName = view.findViewById(R.id.score_player_one);
        mPlayerTwoName = view.findViewById(R.id.score_player_two);
        mPlayerOneText = view.findViewById(R.id.player_one_column);
        mPlayerTwoText = view.findViewById(R.id.player_two_column);
        mBtnGo = view.findViewById(R.id.btn_go);
        mBtnStart = view.findViewById(R.id.btn_start);
    }

    private void setListeners() {


        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player;
                if (counter % 2 == 0) {
                    enableHandle(mPlayerTwoText, mPlayerOneText);
                    player = mPlayerTwo;
                    mColumnSelected = Integer.parseInt(mPlayerOneText.getText().toString());
                    mPlayerOneText.setText("");
                } else {
                    enableHandle(mPlayerOneText, mPlayerTwoText);
                    mColumnSelected = Integer.parseInt(mPlayerTwoText.getText().toString());
                    player = mPlayerOne;
                    mPlayerTwoText.setText("");
                }
                counter++;

                for (int i = mButtons.length - 1; i >= 0; i--) {
                    if (mButtons[i][mColumnSelected].getSolidColor() != getResources().getColor(R.color.green_dark)
                            && mButtons[i][mColumnSelected].getSolidColor() != getResources().getColor(R.color.red)
                            && mButtons[i][mColumnSelected].getSolidColor() != getResources().getColor(R.color.beauty_yellow)) {
                        //   mButtons[mColumnSelected][i].setText("selected");
                        if (player == mPlayerOne) {
                            mButtons[i][mColumnSelected].setBackgroundColor(getResources().getColor(R.color.red));
                            mButtons[i][mColumnSelected].setTextColor(getResources().getColor(R.color.red));
                            index = i;
                            break;
                        } else {
                            mButtons[i][mColumnSelected].setBackgroundColor(getResources().getColor(R.color.beauty_yellow));
                            mButtons[i][mColumnSelected].setTextColor(getResources().getColor(R.color.beauty_yellow));
                            index = i;
                            break;
                        }
                    }

                }

            }

            private void enableHandle(EditText playerEnable, EditText playerDisable) {
                playerDisable.setEnabled(false);
                playerEnable.setEnabled(true);
            }
        });

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayerOneText.setEnabled(true);
                mBtnStart.setVisibility(View.GONE);
            }
        });

    }

    private <T extends View> int[][] createId(T[][] views, String commonPartOfId) {
        int[][] IDs = new int[views.length][views.length];
        for (int i = 0; i < views.length; i++) {
            for (int j = 0; j < views.length; j++) {
                int tempt = getId(commonPartOfId + i, R.id.class);
                IDs[i][j] = tempt;
            }
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

    public void checkWinner() {

    }
}