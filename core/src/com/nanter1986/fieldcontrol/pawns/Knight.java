package com.nanter1986.fieldcontrol.pawns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.fieldcontrol.DisplayToolkit;

/**
 * Created by user on 12/3/2017.
 */

public class Knight extends Pawn {
    public Knight(int x,int y, DisplayToolkit tool,boolean isFriendly) {
        this.tool=tool;
        this.texture=new Texture(Gdx.files.internal("sword.png"));
        this.attack=5;
        this.health=5;
        this.speed=3;
        this.range=1;
        this.positionX=x;
        this.positionY=y;
        this.width=tool.scW/6;
        this.friendly=isFriendly;
    }
}
