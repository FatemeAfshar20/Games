package com.example.games.Model;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.UUID;

public class Player implements Serializable {
    private UUID mId=UUID.randomUUID();
    private  String mUserName;
    private int mScore;
    private ImageButton mImageButton;
    private  int mColorID;

    public Player()  {

    }

    public int getColorID() {
        return mColorID;
    }

    public void setColorID(int colorID) {
        mColorID = colorID;
    }

    public void setId(UUID id) {
        mId = id;
    }



    public UUID getId() {
        return mId;
    }

    public ImageButton getImageButton() {
        return mImageButton;
    }


    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) throws  InputMismatch {
        if(isNumeric(userName.trim()) && userName.equals(""))
           throw new InputMismatch("Wrong Input");
        mUserName = userName;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    private static boolean isNumeric(String strNum) {
        for (char c : strNum.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    private static class InputMismatch extends Exception{
        String msg;
        public InputMismatch(String msg){
                //todo
        }
    }
}
