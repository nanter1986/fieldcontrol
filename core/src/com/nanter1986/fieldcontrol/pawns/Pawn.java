package com.nanter1986.fieldcontrol.pawns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nanter1986.fieldcontrol.DisplayToolkit;

/**
 * Created by user on 12/3/2017.
 */

public abstract class Pawn {
    Texture texture;
    public int positionX;
    public int positionY;
    public int width;
    public boolean selected=false;

    public int health;
    public int attack;
    public int speed;
    public int range;
    public boolean friendly;

    DisplayToolkit tool;

    public void move(){

    }

    public void attack(){

    }

    public void appear(){
        double realX=(tool.scW/5)*(positionX+0.5);
        double realY=(tool.scH-tool.scW)+(tool.scW/5)*(positionY)+(tool.scW/5)*0.5;
        if(selected){
            tool.batch.setColor(Color.RED);
            tool.batch.draw(texture,(int)realX-width/2,(int)realY-width/2,width,width);
            tool.batch.setColor(Color.WHITE);
        }else{
            tool.batch.draw(texture,(int)realX-width/2,(int)realY-width/2,width,width);
        }


    }

    public boolean isTouched(){
        boolean touched=false;
        double realX=(tool.scW/5)*(positionX+0.5);
        double realY=(tool.scH-tool.scW)+(tool.scW/5)*(positionY)+(tool.scW/5)*0.5;
        int x= Gdx.input.getX();
        int y= Gdx.input.getY();
        double leftWall=realX-width/2;
        double rightWall=realX+width/2;
        double topWall=tool.scH-realY-width/2;
        double bottomWall=tool.scH-realY+width/2;
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


}
