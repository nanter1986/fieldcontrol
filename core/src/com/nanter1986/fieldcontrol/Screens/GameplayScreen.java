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

/**
 * Created by user on 12/3/2017.
 */

public class GameplayScreen implements Screen{

    FieldControl game;
    DisplayToolkit tool;
    AdsController adsController;
    private final Texture floortile=new Texture(Gdx.files.internal("tile.png"));

    public GameplayScreen(FieldControl game,AdsController adsController) {
        this.adsController=adsController;
        this.game = game;
        this.tool=new DisplayToolkit(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    }

    private static final Color BACKGROUND_COLOR = new Color(0f, 0f, 0f, 1.0f);

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g,
                BACKGROUND_COLOR.b, BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tool.batch.setProjectionMatrix(tool.camera.combined);
        tool.batch.begin();
        makeFloor();
        
        tool.batch.begin();
    }

    private void makeFloor() {

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
