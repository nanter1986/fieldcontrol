package com.nanter1986.fieldcontrol.pawns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.fieldcontrol.DisplayToolkit;

/**
 * Created by user on 12/3/2017.
 */

public class Wizard extends Pawn{
    public Wizard(int x,int y, DisplayToolkit tool) {
        this.tool=tool;
        this.texture=new Texture(Gdx.files.internal("wizhat.png"));
        this.attack=8;
        this.health=1;
        this.speed=1;
        this.range=4;
        this.positionX=x;
        this.positionY=y;
        this.width=tool.scW/6;
    }
}
