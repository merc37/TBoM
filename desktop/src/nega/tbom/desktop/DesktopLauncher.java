package nega.tbom.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import nega.tbom.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled = false;
		config.foregroundFPS = 0; // Setting to 0 disables foreground fps throttling
		config.width = 1920;
		config.height = 1080;
//		config.backgroundFPS = 0; // Setting to 0 disables background fps throttling
		new LwjglApplication(new MainGame(), config);
	}
}
