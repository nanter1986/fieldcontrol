package com.nanter1986.fieldcontrol.pawns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.fieldcontrol.DisplayToolkit;

/**
 * Created by user on 12/3/2017.
 */

public class Ninja extends Pawn{
    public Ninja(int x,int y, DisplayToolkit tool) {
        this.tool=tool;
        this.texture=new Texture(Gdx.files.internal("katana.png"));
        this.attack=3;
        this.health=1;
        this.speed=7;
        this.range=3;
        this.positionX=x;
        this.positionY=y;
        this.width=tool.scW/6;
    }
}
