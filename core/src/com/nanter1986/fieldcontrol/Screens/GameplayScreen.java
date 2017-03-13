package com.nanter1986.fieldcontrol.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.nanter1986.fieldcontrol.AdsController;
import com.nanter1986.fieldcontrol.DisplayToolkit;
import com.nanter1986.fieldcontrol.FieldControl;
import com.nanter1986.fieldcontrol.buttons.FloorTile;
import com.nanter1986.fieldcontrol.pawns.Archer;
import com.nanter1986.fieldcontrol.pawns.Knight;
import com.nanter1986.fieldcontrol.pawns.Ninja;
import com.nanter1986.fieldcontrol.pawns.Pawn;
import com.nanter1986.fieldcontrol.pawns.Shield;
import com.nanter1986.fieldcontrol.pawns.Wizard;

import java.util.ArrayList;

/**
 * Created by user on 12/3/2017.
 */

public class GameplayScreen implements Screen {

    FieldControl game;
    DisplayToolkit tool;
    AdsController adsController;

    private float tileHeight;
    ArrayList<Pawn> pawns;
    ArrayList<Pawn> graveyard;
    private boolean actionSelect = false;

    ArrayList<FloorTile> floor;

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
        graveyard = new ArrayList<Pawn>();
        pawns = new ArrayList<Pawn>();
        floor = new ArrayList<FloorTile>();
        pawns.add(new Knight(0, 0, tool, true));
        pawns.add(new Archer(1, 1, tool, true));
        pawns.add(new Ninja(2, 1, tool, true));
        pawns.add(new Shield(1, 2, tool, true));

        pawns.add(new Wizard(4, 4, tool, false));
        pawns.add(new Knight(3, 3, tool, false));
        pawns.add(new Archer(4, 3, tool, false));
        pawns.add(new Shield(3, 4, tool, false));

        makeFloor();
    }

    @Override
    public void render(float delta) {
        controlCheck();
        tool.camera.update();
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        for (FloorTile f : floor) {
            f.appear();
        }
        for (Pawn p : pawns) {
            p.appear();
        }
        tool.batch.end();
    }

    private void controlCheck() {
        for (Pawn p : pawns) {
            if (p.isTouched() && p.selected && p.friendly) {
                p.selected = false;
                actionSelect = false;
                break;
            } else if (p.isTouched() && actionSelect == false && p.friendly) {
                p.selected = true;
                actionSelect = true;
                break;
            } else if (p.selected) {
                for (FloorTile f : floor) {
                    if (f.isButtonTouched()) {
                        if (checkIfTileIsFree(f) && checkIfCloseEnough(f, p)) {
                            p.positionX = f.buttonX;
                            p.positionY = f.buttonY;
                            p.selected = false;
                            actionSelect = false;
                        }


                    }
                }
            } else if (actionSelect && !p.friendly && p.isTouched()) {
                for (Pawn attacker : pawns) {
                    if (attacker.selected &&
                            attacker.speed >= Math.abs(attacker.positionX - p.positionX)
                            && attacker.speed >= Math.abs(attacker.positionY - p.positionY)) {
                        p.health=p.health-attacker.attack;
                        if(p.health<=0){
                            graveyard.add(p);


                        }

                    }
                }

            }

        }
        pawns.removeAll(graveyard);
    }

    private boolean checkIfCloseEnough(FloorTile f, Pawn p) {
        boolean closeEnough = false;
        if (p.speed >= Math.abs(p.positionX - f.buttonX) && p.speed >= Math.abs(p.positionY - f.buttonY)) {
            if (p.positionX == f.buttonX || p.positionY == f.buttonY) {
                closeEnough = true;
            }
        } else {
            customLog("cant reach");
        }
        return closeEnough;
    }

    private boolean checkIfTileIsFree(FloorTile f) {
        boolean free = true;
        for (Pawn p : pawns) {
            if (f.buttonX == p.positionX && f.buttonY == p.positionY) {
                free = false;
                break;
            } else {
                customLog("Cell not free");
            }
        }

        return free;
    }

    private void makeFloor() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int x = i;
                int y = j;
                floor.add(new FloorTile(tool, x, y));
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

    public void customLog(String m) {
        Gdx.app.log("myLog", m);
    }
}
