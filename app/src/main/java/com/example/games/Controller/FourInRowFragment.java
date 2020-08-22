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
    int mNumBtn = 25;
    TextView mPlayerOneName, mPlayerTwoName;
    private ImageButton mBtnGo, mBtnStart;
    private Button[] mButtons = new Button[mNumBtn];
    int[] mResId = createId(mButtons, "btn_");
    boolean[][] isSelected=new boolean[mNumBtn][mNumBtn];
    Button[][] mButtons2D;
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
        isSelected(isSelected);
        mButtons2D=make2DArrayBtns(mNumBtn);
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
                    mButtons[i]=view.findViewById(mResId[i]);
        }

        mPlayerOneName = view.findViewById(R.id.score_player_one);
        mPlayerTwoName = view.findViewById(R.id.score_player_two);
        mPlayerOneText = view.findViewById(R.id.player_one_column);
        mPlayerTwoText = view.findViewById(R.id.player_two_column);
        mBtnGo = view.findViewById(R.id.btn_go);
        mBtnStart = view.findViewById(R.id.btn_start);
    }

    public Button[][] make2DArrayBtns(int numBtn){
        Button[][] btnArray2D=new Button[5][5];
        index=0;
        for (int i = 0; i <5 ; i++) {
            for (int j = 0; j <5 ; j++) {
                btnArray2D[i][j]=mButtons[index];
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

               for (int i = mButtons2D.length - 1; i >= 0; i--) {
                    if (!isSelected[i][mColumnSelected]) {
                         isSelected[i][mColumnSelected]=true;
                        if (player == mPlayerOne) {
                            mButtons2D[i][mColumnSelected].setBackgroundColor(getResources().getColor(R.color.red));
                            mButtons2D[i][mColumnSelected].setTextColor(getResources().getColor(R.color.red));
                        } else {
                            mButtons2D[i][mColumnSelected].setBackgroundColor(getResources().getColor(R.color.beauty_yellow));
                            mButtons2D[i][mColumnSelected].setTextColor(getResources().getColor(R.color.beauty_yellow));
                        }
                        break;
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

    void isSelected(boolean[][] booleans){
        for (int i = 0; i <booleans.length ; i++) {
            for (int j = 0; j <booleans.length ; j++) {
                booleans[i][j]=false;
            }
        }
    }

    public void checkWinner() {

    }
}