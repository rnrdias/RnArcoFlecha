package com.rnarcfec.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rnarcfec.game.RnArcFecGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = 853;
                config.height = 480;
                //config.y = 0;
		new LwjglApplication(new RnArcFecGame(), config);
                //new LwjglApplication(new UIDemo(),config);
	}
}
