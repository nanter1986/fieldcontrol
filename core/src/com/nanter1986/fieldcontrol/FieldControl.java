package com.nanter1986.fieldcontrol;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.nanter1986.fieldcontrol.Screens.GameplayScreen;

public class FieldControl extends Game implements AdsController{


	FieldControl enGame;
	private AdsController adsController;

	public FieldControl(AdsController adsController) {
		this.adsController=adsController;
		this.enGame = this;
	}

	public FieldControl() {
		this.enGame = this;
	}

	@Override
	public void create () {
		if(Gdx.app.getType() == Application.ApplicationType.Android){
			setScreen(new GameplayScreen(enGame,adsController));
		}else{
			setScreen(new GameplayScreen(enGame));
		}


	}

	@Override
	public void showOrLoadInterstitialAd() {

	}
}
