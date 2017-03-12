package com.nanter1986.fieldcontrol.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nanter1986.fieldcontrol.FieldControl;
import com.nanter1986.fieldcontrol.Screens.GameplayScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable=false;
		config.height=600;
		config.width=400;
		config.title="Field Control";
		new LwjglApplication(new FieldControl(), config);
	}
}
