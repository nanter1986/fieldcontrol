package com.nanter1986.fieldcontrol;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by user on 12/3/2017.
 */

public class DisplayToolkit {
    public Preferences prefs;
    public SpriteBatch batch;
    public BitmapFont font;
    public OrthographicCamera camera;
    public int scW;
    public int scH;



    public DisplayToolkit(int screenWidth,int screenHeight) {
        prefs= Gdx.app.getPreferences("FieldControl");
        scW=screenWidth;
        scH=screenHeight;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(screenWidth, screenHeight);
        camera.position.set(screenWidth / 2, screenHeight / 2, 0);

        font=new BitmapFont();
        font.setColor(0.5f, 0.5f, 0.5f, 1.0f);



    }
}
