package com.nanter1986.fieldcontrol.pawns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.fieldcontrol.DisplayToolkit;

/**
 * Created by user on 12/3/2017.
 */

public class Shield extends Pawn{
    public Shield(int x,int y, DisplayToolkit tool,boolean isFriendly) {
        this.tool=tool;
        this.texture=new Texture(Gdx.files.internal("shield.png"));
        this.attack=0;
        this.health=14;
        this.speed=1;
        this.range=0;
        this.positionX=x;
        this.positionY=y;
        this.width=tool.scW/6;
        this.friendly=isFriendly;
    }
}
