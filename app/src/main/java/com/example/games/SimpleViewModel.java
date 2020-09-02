package com.example.games;

import androidx.lifecycle.ViewModel;

public class SimpleViewModel extends ViewModel {

   private String userNameOne="";
   private String userNameTwo="";

    public String getUserNameOne() {
        return userNameOne;
    }

    public void setUserNameOne(String userNameOne) {
        this.userNameOne = userNameOne;
    }

    public String getUserNameTwo() {
        return userNameTwo;
    }

    public void setUserNameTwo(String userNameTwo) {
        this.userNameTwo = userNameTwo;
    }
}
