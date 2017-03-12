package com.nanter1986.fieldcontrol;

import com.badlogic.gdx.Game;
import com.nanter1986.fieldcontrol.Screens.GameplayScreen;

public class FieldControl extends Game {


	FieldControl enGame;
	private AdsController adsController;

	public FieldControl(AdsController adsController) {
		this.adsController=adsController;
		this.enGame = this;


	}

	@Override
	public void create () {
		setScreen(new GameplayScreen(enGame,adsController));

	}
}
