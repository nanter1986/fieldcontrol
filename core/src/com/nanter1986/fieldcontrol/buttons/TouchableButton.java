package com.nanter1986.fieldcontrol.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.fieldcontrol.DisplayToolkit;

/**
 * Created by user on 12/3/2017.
 */

public abstract class TouchableButton {
    public int buttonX;
    public int buttonY;
    public int buttonW;
    public int buttonH;
    public Texture texture;
    public boolean touchedOnce;
    public float screenW;
    public float screenH;


    public TouchableButton(DisplayToolkit tool) {
        this.screenH=tool.scH;
        this.screenW=tool.scW;

    }


    public boolean isButtonTouched(){
        boolean t=false;
        int x= Gdx.input.getX();
        int y= Gdx.input.getY();

        if(Gdx.input.justTouched() && x>buttonX && x<buttonX+buttonW && y<screenH-buttonY && y>screenH-buttonY-buttonH){
            t=true;
        }
        return t;

    }
}
