package com.nanter1986.fieldcontrol.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.nanter1986.fieldcontrol.AdsController;
import com.nanter1986.fieldcontrol.DisplayToolkit;
import com.nanter1986.fieldcontrol.FieldControl;
import com.nanter1986.fieldcontrol.pawns.Archer;
import com.nanter1986.fieldcontrol.pawns.Knight;
import com.nanter1986.fieldcontrol.pawns.Ninja;
import com.nanter1986.fieldcontrol.pawns.Pawn;
import com.nanter1986.fieldcontrol.pawns.Shield;

import java.util.ArrayList;

/**
 * Created by user on 12/3/2017.
 */

public class GameplayScreen implements Screen {

    FieldControl game;
    DisplayToolkit tool;
    AdsController adsController;
    private final Texture floortile = new Texture(Gdx.files.internal("tile.png"));
    private float tileHeight;
    ArrayList<Pawn> pawns;
    private boolean actionSelect=false;

    public GameplayScreen(FieldControl game, AdsController adsController) {
        this.adsController = adsController;
        this.game = game;
        this.tool = new DisplayToolkit(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.tileHeight = tool.scW / 5;

    }

    public GameplayScreen(FieldControl game) {
        this.game = game;
        this.tool = new DisplayToolkit(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.tileHeight = tool.scW / 5;

    }

    private static final Color BACKGROUND_COLOR = new Color(0f, 0f, 0f, 1.0f);

    @Override
    public void show() {
        pawns = new ArrayList<Pawn>();
        pawns.add(new Knight(0, 0, tool));
        pawns.add(new Archer(1, 1, tool));
        pawns.add(new Ninja(2, 1, tool));
        pawns.add(new Shield(1, 2, tool));
    }

    @Override
    public void render(float delta) {
        for (Pawn p : pawns) {
            if (p.selected) {
                actionSelect=true;
            }
        }

        if(!actionSelect){
            for (Pawn p : pawns) {
                if (p.isTouched()) {
                    p.selected = true;
                    break;
                }
            }
        }else{
            for (Pawn p : pawns) {
                if (p.isTouched() && p.selected) {
                    p.selected = false;
                    break;
                }
            }
        }



        tool.camera.update();
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        makeFloor();
        for (Pawn p : pawns) {
            p.appear();
        }
        tool.batch.end();
    }

    private void makeFloor() {
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 6; j++) {
                tool.batch.draw(floortile, tileHeight * i, tool.scH - tileHeight * j, tileHeight, tileHeight);
            }
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
