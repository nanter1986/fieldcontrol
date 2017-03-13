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
    private boolean itsPlayerOneTurn=true;

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
        pawns.add(new Ninja(0, 1, tool, true));
        pawns.add(new Wizard(1, 0, tool, true));

        pawns.add(new Wizard(4, 4, tool, false));
        pawns.add(new Knight(3, 3, tool, false));
        pawns.add(new Archer(4, 3, tool, false));
        pawns.add(new Shield(3, 4, tool, false));

        makeFloor();
    }

    @Override
    public void render(float delta) {
        checkForEndOfGame();
        if(itsPlayerOneTurn){
            controlCheck();
        }else{
            moveByAI();
        }

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
            p.isHovered();
        }
        tool.batch.end();
    }

    private void checkForEndOfGame() {
        ArrayList<Pawn>aiPawns=makeAiArraylist();
        ArrayList<Pawn>playerPawns=makePlayerArraylist();
        if(aiPawns.size()==0){
            customLog("Player won");
        }else if(playerPawns.size()==0){
            customLog("AI won");
        }
    }

    private void moveByAI() {
        ArrayList<Pawn>aiPawns=makeAiArraylist();
        ArrayList<Pawn>playerPawns=makePlayerArraylist();
        outerloop:
        for (Pawn aiPawn : aiPawns) {
            for(Pawn playerPawn: playerPawns){
                //search for attack on same X
                if((aiPawn.attack>0 &&
                        aiPawn.range>=Math.abs(aiPawn.positionY-playerPawn.positionY)) &&
                        (aiPawn.positionX==playerPawn.positionX )){
                    customLog("1");
                    playerPawn.health=playerPawn.health-aiPawn.attack;
                    customLog("Received "+aiPawn.attack+" damage");
                    if(playerPawn.health<=0){
                        graveyard.add(playerPawn);
                    }
                    itsPlayerOneTurn=true;
                    break outerloop;
                    //search for attack on same Y
                }else if ((aiPawn.attack>0 &&
                        aiPawn.range>=Math.abs(aiPawn.positionX-playerPawn.positionX)) &&
                        (aiPawn.positionY==playerPawn.positionY )){
                    customLog("2");
                    playerPawn.health=playerPawn.health-aiPawn.attack;
                    customLog("Received "+aiPawn.attack+" damage");
                    if(playerPawn.health<=0){
                        graveyard.add(playerPawn);
                    }
                    itsPlayerOneTurn=true;
                    break outerloop;
                    //approach  on same X from high Y NOT enough speed
                }else if(playerPawn.positionX==aiPawn.positionX&&
                        aiPawn.speed<=Math.abs(playerPawn.positionY-aiPawn.positionY-1)&&
                        aiPawn.positionY>playerPawn.positionY
                        && checkIfcellIsFree(aiPawn.positionX,aiPawn.positionY-aiPawn.speed)){
                    customLog("3");
                    aiPawn.positionY=aiPawn.positionY-aiPawn.speed;
                    itsPlayerOneTurn=true;
                    break outerloop;
                    //approach  on same X from low Y NOT enough speed
                }else if(playerPawn.positionX==aiPawn.positionX&&
                        aiPawn.speed<=Math.abs(playerPawn.positionY-aiPawn.positionY-1)&&
                        aiPawn.positionY<playerPawn.positionY
                        && checkIfcellIsFree(aiPawn.positionX,aiPawn.positionY+aiPawn.speed)){
                    customLog("4");
                    aiPawn.positionY=playerPawn.positionY+aiPawn.speed;
                    itsPlayerOneTurn=true;
                    break outerloop;
                    //approach  on same X from low enough speed
                }else if(playerPawn.positionX==aiPawn.positionX&&
                        aiPawn.speed>=Math.abs(playerPawn.positionY-aiPawn.positionY-1)&&
                        aiPawn.positionY<playerPawn.positionY
                        && checkIfcellIsFree(aiPawn.positionX,aiPawn.positionY-1)){
                    customLog("5");
                    aiPawn.positionY=playerPawn.positionY-1;
                    itsPlayerOneTurn=true;
                    break outerloop;
                    //approach  on same X from low Y enough speed
                }else if(playerPawn.positionX==aiPawn.positionX&&
                        aiPawn.speed>=Math.abs(playerPawn.positionY-aiPawn.positionY-1)&&
                        aiPawn.positionY>playerPawn.positionY
                        && checkIfcellIsFree(aiPawn.positionX,aiPawn.positionY+1)){
                    customLog("6");
                    aiPawn.positionY=playerPawn.positionY+1;
                    itsPlayerOneTurn=true;
                    break outerloop;
                    //approach close on X
                }else if(aiPawn.speed>=Math.abs(playerPawn.positionX-aiPawn.positionX)){
                    customLog("7");
                    aiPawn.positionX=playerPawn.positionX;
                    itsPlayerOneTurn=true;
                    break outerloop;
                    //approach close on Y
                }else if(aiPawn.speed>=Math.abs(playerPawn.positionY-aiPawn.positionY)){
                    customLog("8");
                    aiPawn.positionY=playerPawn.positionY;
                    itsPlayerOneTurn=true;
                    break outerloop;
                }else{
                    customLog("101");
                }
            }

        }
        pawns.removeAll(graveyard);
    }

    private boolean checkIfcellIsFree(int x, int y) {
        boolean free=true;
        for(Pawn p:pawns){
            if(p.positionX==x && p.positionY==y){
                free=false;
                break;
            }
        }
        return free;
    }

    private ArrayList<Pawn> makePlayerArraylist() {
        ArrayList<Pawn>temp=new ArrayList<Pawn>();
        for (Pawn p : pawns){
            if(p.friendly) {
                temp.add(p);
            }
        }
        return temp;
    }

    private ArrayList<Pawn> makeAiArraylist() {
        ArrayList<Pawn>temp=new ArrayList<Pawn>();
        for (Pawn p : pawns){
            if(!p.friendly) {
                temp.add(p);
            }
        }
        return temp;
    }

    private void makeAndShortTwoArraylists(ArrayList<Pawn> aiPawns, ArrayList<Pawn> playerPawns) {

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
                            itsPlayerOneTurn=false;
                        }


                    }
                }
            } else if (actionSelect && !p.friendly && p.isTouched()) {
                for (Pawn attacker : pawns) {
                    if (attacker.selected &&
                            attacker.range >= Math.abs(attacker.positionX - p.positionX)
                            && attacker.range >= Math.abs(attacker.positionY - p.positionY)) {
                        p.health=p.health-attacker.attack;
                        customLog("Dealt "+attacker.attack+" damage");
                        if(p.health<=0){
                            graveyard.add(p);
                        }
                        itsPlayerOneTurn=false;
                        actionSelect=false;
                        attacker.selected=false;

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
        }
        return closeEnough;
    }

    private boolean checkIfTileIsFree(FloorTile f) {
        boolean free = true;
        for (Pawn p : pawns) {
            if (f.buttonX == p.positionX && f.buttonY == p.positionY) {
                free = false;
                break;
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
