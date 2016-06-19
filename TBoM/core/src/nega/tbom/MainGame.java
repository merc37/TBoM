package nega.tbom;

import nega.tbom.screens.LoadScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class MainGame extends Game {
	
	private Screen MENU, GAME, PAUSE;
	
	@Override
	public void create () {
		this.setScreen(new LoadScreen(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
}
