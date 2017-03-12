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
    DisplayToolkit tool;


    public TouchableButton(DisplayToolkit tool) {
        this.screenH=tool.scH;
        this.screenW=tool.scW;
        this.tool=tool;

    }


    public boolean isButtonTouched(){
        /*boolean t=false;
        int x= Gdx.input.getX();
        int y= Gdx.input.getY();

        if(Gdx.input.justTouched() && x>buttonX && x<buttonX+buttonW && y<screenH-buttonY && y>screenH-buttonY-buttonH){
            t=true;
        }
        return t;*/
        boolean touched=false;
        double realX=(tool.scW/5)*(buttonX);
        double realY=(tool.scH-tool.scW)+(tool.scW/5)*(buttonY);
        int x= Gdx.input.getX();
        int y= Gdx.input.getY();
        double leftWall=realX;
        double rightWall=realX+buttonW;
        double topWall=tool.scH-realY-buttonW;
        double bottomWall=tool.scH-realY;
        if(Gdx.input.justTouched() &&
                x>leftWall &&
                x<rightWall &&
                y>topWall &&
                y<bottomWall){
            touched=true;
            Gdx.app.log("touched",""+this.getClass().toString());


        }

        return touched;

    }

    public void appear(){
        double realX=(tool.scW/5)*(buttonX);
        double realY=(tool.scH-tool.scW)+(tool.scW/5)*(buttonY);
        tool.batch.draw(texture, (int)realX, (int)realY, buttonW, buttonH);
    }
}
