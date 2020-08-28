package com.example.games.Controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.games.Model.Player;
import com.example.games.R;

import java.lang.reflect.Field;

public class FourInRowFragment extends Fragment {
   private int mNumBtn = 25;
    private TextView mPlayerOneName, mPlayerTwoName;
    private ImageButton mBtnGo, mBtnStart;
    private Button[] mButtons = new Button[mNumBtn];
    private int[] mResId = createId(mButtons, "btn_");
    private boolean[][] isSelected = new boolean[mNumBtn][mNumBtn];
    private Button[][] mButtons2D;
    private Player mPlayerOne = new Player(),mPlayerTwo = new Player();
    private EditText mPlayerOneText, mPlayerTwoText;
    private int mCounter = 0,mColumnSelected;
    private int[][] mSelected=new int[5][5];

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
        isSelected(isSelected);
        mButtons2D = make2DArrayBtns(mNumBtn);
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
            mButtons[i] = view.findViewById(mResId[i]);
        }

        mPlayerOneName = view.findViewById(R.id.score_player_one);
        mPlayerTwoName = view.findViewById(R.id.score_player_two);
        mPlayerOneText = view.findViewById(R.id.player_one_column);
        mPlayerTwoText = view.findViewById(R.id.player_two_column);
        mBtnGo = view.findViewById(R.id.btn_go);
        mBtnStart = view.findViewById(R.id.btn_start);
    }

    public Button[][] make2DArrayBtns(int numBtn) {
        Button[][] btnArray2D = new Button[5][5];
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                btnArray2D[i][j] = mButtons[index];
                index++;
            }
        }
        return btnArray2D;
    }

    private void setListeners() {


        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player;
                if (mCounter % 2 == 0) {
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
                mCounter++;

                for (int i = mButtons2D.length - 1; i >= 0; i--) {
                    if (mSelected[i][mColumnSelected-1]==0) {
                        if (player == mPlayerOne) {
                            mSelected[i][mColumnSelected-1]=1;
                            mButtons2D[i][mColumnSelected - 1].setBackgroundColor(getResources().getColor(R.color.red));
                            mButtons2D[i][mColumnSelected - 1].setTextColor(getResources().getColor(R.color.red));
                        } else {
                            mSelected[i][mColumnSelected-1]=2;
                            mButtons2D[i][mColumnSelected - 1].setBackgroundColor(getResources().getColor(R.color.beauty_yellow));
                            mButtons2D[i][mColumnSelected - 1].setTextColor(getResources().getColor(R.color.beauty_yellow));
                        }
                        break;
                    }
                }

                if(checkWinner()==1)
                    Toast.makeText(getActivity(),mPlayerOne.getUserName()+" winner"
                            ,Toast.LENGTH_LONG).show();
                else if(checkWinner()==2)
                    Toast.makeText(getActivity(),mPlayerTwo.getUserName()+" winner"
                            ,Toast.LENGTH_LONG).show();

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

    private <T extends View> int[] createId(T[] views, String commonPartOfId) {
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

    void isSelected(boolean[][] booleans) {
        for (int i = 0; i < booleans.length; i++) {
            for (int j = 0; j < booleans.length; j++) {
                booleans[i][j] = false;
            }
        }
    }

    public int checkWinner() {
        int counter;
        int winner = 0;
        for (int i = mButtons2D.length - 1; i >= 0; i--) {
            counter = 0;
            for (int j = 0; j < mButtons2D.length - 1; j++) {
                if (mSelected[i][j] != 0
                        && mSelected[i][j] == mSelected[i][j + 1]) {
                    counter++;
                    winner = mSelected[i][j];
                }
            }
            if (counter == 3)
                return winner;
        }

        for (int i = 0; i <mButtons2D.length - 1; i++) {
            counter = 0;
            for (int j = mButtons2D.length - 2; j >= 0;j--) {
                if (mSelected[j][i] != 0
                        && mSelected[j][i] == mSelected[j- 1][i]) {
                    counter++;
                    winner = mSelected[j][i];
                }
            }
            if (counter == 3)
                return winner;
        }

        for (int i = mButtons2D.length - 2; i >= 0; i--) {
            counter = 0;
            for (int j = 0; j < mButtons2D.length - 1; j++) {
                if (mSelected[i][j] != 0
                        && mSelected[i][j] == mSelected[i-1][j + 1]) {
                    counter++;
                    winner = mSelected[i][j];
                }
            }
            if (counter == 3)
                return winner;
        }


        return 0;

    }
}