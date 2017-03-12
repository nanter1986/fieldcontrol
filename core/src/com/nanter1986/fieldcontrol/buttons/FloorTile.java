package com.nanter1986.fieldcontrol.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.fieldcontrol.DisplayToolkit;

/**
 * Created by user on 12/3/2017.
 */

public class FloorTile extends TouchableButton{
    public FloorTile(DisplayToolkit tool,int x,int y) {
        super(tool);
        texture = new Texture(Gdx.files.internal("tile.png"));
        buttonX=x;
        buttonY=y;
        buttonW=tool.scW/5;
        buttonH=tool.scW/5;
    }
}
