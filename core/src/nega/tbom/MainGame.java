package nega.tbom;

import nega.tbom.screens.GameScreen;
import nega.tbom.screens.MainMenuScreen;
import nega.tbom.screens.PauseScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class MainGame extends Game {
	
	private Screen MENU, GAME, PAUSE;
	
	@Override
	public void create () {
		MENU = new MainMenuScreen(this);
		GAME = new GameScreen(this);
		PAUSE = new PauseScreen(this);
		
		this.setScreen(MENU);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
}
