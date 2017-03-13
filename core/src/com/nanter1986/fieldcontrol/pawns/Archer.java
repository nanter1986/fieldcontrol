package com.nanter1986.fieldcontrol.pawns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.fieldcontrol.DisplayToolkit;

/**
 * Created by user on 12/3/2017.
 */

public class Archer extends Pawn {
    public Archer(int x, int y, DisplayToolkit tool,boolean isFriendly) {
        this.tool=tool;
        this.texture=new Texture(Gdx.files.internal("bow.png"));
        this.attack=3;
        this.health=3;
        this.speed=3;
        this.range=5;
        this.positionX=x;
        this.positionY=y;
        this.width=tool.scW/6;
        this.friendly=isFriendly;
    }
}
