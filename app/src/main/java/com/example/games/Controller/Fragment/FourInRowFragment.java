package com.example.games.Controller.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.games.Model.Player;
import com.example.games.R;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Field;

public class FourInRowFragment extends Fragment {
    public static final String BUNDLE_COLOR_ID = "Color Id";
    public static final String BUNDLE_COUNTER = "counter";
    public static final String BUNDLE_START_VISIBILITY = "Start Visibility";
    private int mNumBtn = 25;
    int mSqrtNumBtn = (int) Math.sqrt(Double.parseDouble(mNumBtn + ""));
    private TextView mPlayerOneName, mPlayerTwoName;
    private ImageButton mBtnGo, mBtnStart;
    private Button mBtnRefresh;
    private Button[] mButtons = new Button[mNumBtn];
    private Button[][] mButtons2D;
    private EditText mPlayerOneText, mPlayerTwoText;
    private ViewGroup mLayTable;
    private Player mPlayerOne = new Player(), mPlayerTwo = new Player();
    private int[] mResId = createId(mButtons, "btn_");
    private int mCounter = 0, mColumnSelected;
    private int[][] mSelected = new int[mSqrtNumBtn][mSqrtNumBtn];
    private int[] mColorId=new int[mNumBtn];

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
      saveInstance(savedInstanceState);
        mButtons2D = make2DArrayBtns(mSqrtNumBtn);
       // setRetainInstance(true);
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

        // Find Button
        for (int i = 0; i < mButtons.length; i++) {
            mButtons[i] = view.findViewById(mResId[i]);
        }

        mPlayerOneName = view.findViewById(R.id.player_one_name);
        mPlayerTwoName = view.findViewById(R.id.player_two_name);
        mPlayerOneText = view.findViewById(R.id.player_one_column);
        mPlayerTwoText = view.findViewById(R.id.player_two_column);
        mBtnGo = view.findViewById(R.id.btn_go);
        mBtnStart = view.findViewById(R.id.btn_start);
        mLayTable=view.findViewById(R.id.table);
        mBtnRefresh=view.findViewById(R.id.btn_refresh);

    }

    // convert Button[] to Button[][]

    public Button[][] make2DArrayBtns(int numBtn) {
        Button[][] btnArray2D = new Button[numBtn][numBtn];
        int index = 0;
        for (int i = 0; i < btnArray2D.length; i++) {
            for (int j = 0; j < btnArray2D.length; j++) {
                btnArray2D[i][j] = mButtons[index];
                index++;
            }
        }
        return btnArray2D;
    }

    private void setListeners() {

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayerOneText.setEnabled(true);
                mBtnStart.setVisibility(View.GONE);
            }
        });

        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setPlayer();
                mCounter++;

                if (checkWinner() == 1) {
                    finishGame(mPlayerOneName, mPlayerOne);
                } else if (checkWinner() == 2) {
                    finishGame(mPlayerTwoName, mPlayerTwo);
                } else if (mCounter == mNumBtn) {
                    equLGame();
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

    private void setPlayer() {
        if (mCounter % 2 == 0) {
            enableHandle(mPlayerTwoText, mPlayerOneText);
            // Player one
            if (isValid(mPlayerOneText))
                changeBgColorSelected(R.color.beauty_yellow, 2);
        } else {
            enableHandle(mPlayerOneText, mPlayerTwoText);
            // Player two
            if (isValid(mPlayerTwoText))
                changeBgColorSelected(R.color.red, 1);
        }
    }

    private void enableHandle(EditText playerEnable, EditText playerDisable) {
        playerDisable.setEnabled(false);
        playerEnable.setEnabled(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_COUNTER,mCounter);
        outState.putInt(BUNDLE_START_VISIBILITY,mBtnStart.getVisibility());
        outState.putIntArray(BUNDLE_COLOR_ID,mColorId);
    }

    private void saveInstance(Bundle bundle){
        if(bundle!=null) {
            int[] colors = bundle.getIntArray(BUNDLE_COLOR_ID);

            for (int i = colors.length-1; i >=0; i--) {
                if (colors[i] != 0)
                    mButtons[i].setBackgroundColor(colors[i]);
            }

            mCounter=bundle.getInt(BUNDLE_COUNTER)+1;
            mBtnStart.setVisibility(bundle.getInt(BUNDLE_START_VISIBILITY));
        }
    }

    private boolean isValid(EditText columnSelected) {
        mColumnSelected=getColumnSelected(columnSelected)-1;
            if(mColumnSelected<Math.sqrt(mNumBtn) && mColumnSelected>=0){
                return true;
            }
        else
            returnSnackbar(R.string.error_out_band, R.color.red);
        return false;
    }

    private void finishGame(TextView winner, Player player) {
        handleElemFinishGame();
        winner.setText(winner.getText()+" winner!!");
        winner.setTextColor(getResources().getColor(R.color.red));
        returnSnackbar(player.getUserName() + " Winner", R.color.green_dark);
        mBtnRefresh.setVisibility(View.VISIBLE);
        mCounter=0;
    }

    private void equLGame() {
        handleElemFinishGame();
        mPlayerTwoText.setTextColor(getResources().getColor(R.color.red));
        mPlayerOneText.setTextColor(getResources().getColor(R.color.red));
        returnSnackbar(mPlayerTwo.getUserName() + " " + mPlayerOne.getUserName() + " are winner", R.color.green_dark);
    }

    private void handleElemFinishGame() {
        mPlayerTwoText.setEnabled(false);
        mPlayerOneText.setEnabled(false);
        mPlayerTwoText.setVisibility(View.GONE);
        mPlayerOneText.setVisibility(View.GONE);
        mLayTable.setVisibility(View.GONE);
        mBtnGo.setVisibility(View.GONE);
    }

    private void handleElemRefreshGame() {
        mPlayerTwoText.setEnabled(true);
        mPlayerOneText.setEnabled(true);
        mPlayerTwoText.setVisibility(View.VISIBLE);
        mPlayerOneText.setVisibility(View.VISIBLE);
        mLayTable.setVisibility(View.VISIBLE);
        mBtnGo.setVisibility(View.VISIBLE);
        mBtnRefresh.setVisibility(View.GONE);
        mBtnStart.setVisibility(View.VISIBLE);
    }
    private void refreshGame(){
        mSelected = new int[mSqrtNumBtn][mSqrtNumBtn];
        for (int i = 0; i <mButtons2D.length ; i++) {
            for (int j = 0; j <mButtons2D.length ; j++) {
                mButtons2D[i][j].setBackgroundColor(getResources().getColor(R.color.green_dark));
            }
        }
        mCounter=0;
        handleElemRefreshGame();
        mPlayerOneName.setText(mPlayerOne.getUserName() + "");
        mPlayerTwoName.setText(mPlayerTwo.getUserName() + "");
        mPlayerOneText.setEnabled(false);
        mPlayerTwoText.setEnabled(false);

        mPlayerOneName.setTextColor(Color.BLACK);
        mPlayerTwoName.setTextColor(Color.BLACK);

    }

    private void changeBgColorSelected(int colorId, int playerId) {
        for (int i = mButtons2D.length - 1; i >= 0; i--) {
            if (mSelected[i][mColumnSelected] == 0) {
                mSelected[i][mColumnSelected] = playerId;
                mButtons2D[i][mColumnSelected].setBackgroundColor(getResources().getColor(colorId));
                mColorId[i]=colorId;
                mButtons2D[i][mColumnSelected].setTextColor(getResources().getColor(colorId));
                break;
            }
        }
    }

    private int getColumnSelected(EditText selectedColumnText) {
        int columnSelected = Integer.parseInt(selectedColumnText.getText().toString());
        selectedColumnText.setText("");
        return columnSelected;
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

        for (int i = 0; i < mButtons2D.length - 1; i++) {
            counter = 0;
            for (int j = mButtons2D.length - 1; j >= 0; j--) {
                if (mSelected[j][i] != 0
                        && mSelected[j][i] == mSelected[j - 1][i]) {
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
                        && mSelected[i][j] == mSelected[i - 1][j + 1]) {
                    counter++;
                    winner = mSelected[i][j];
                }
            }
            if (counter == 3)
                return winner;
        }
        return 0;
    }

    private void returnSnackbar(int msg, int color) {
        Snackbar snackbar = Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG);
        snackbar.setTextColor(getResources().getColor(R.color.white));
        snackbar.setBackgroundTint(getResources().getColor(color));
        snackbar.show();
    }

    private void returnSnackbar(String msg, int color) {
        Snackbar snackbar = Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG);
        snackbar.setTextColor(getResources().getColor(R.color.white));
        snackbar.setBackgroundTint(getResources().getColor(color));
        snackbar.show();
    }

}