package com.example.games.Model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.UUID;

public class Player implements Serializable {
    private UUID mId=UUID.randomUUID();
    private  String mUserName;
    private static int mScore=0;
    private Drawable mImage;
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

    public void setImage(Drawable image) {
        mImage = image;
    }

    public Drawable getImage() {
        return mImage;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName)  {
        mUserName = userName;
    }

    public static int  getScore() {
        return mScore;
    }

    public static void setScore(int score) {
        mScore = score;
    }

}
