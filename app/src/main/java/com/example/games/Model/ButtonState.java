package com.example.games.Model;

import android.widget.Button;
import android.widget.ImageButton;

import java.util.UUID;

public class ButtonState {
    private UUID mId=UUID.randomUUID();
    private ImageButton mBtn;
    private boolean isSelect;

    public UUID getId() {
        return mId;
    }

    public ImageButton getBtn() {
        return mBtn;
    }

    public void setBtn(ImageButton btn) {
        mBtn = btn;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
