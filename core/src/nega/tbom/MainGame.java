package nega.tbom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import nega.tbom.screens.*;

public class MainGame extends Game {
	
	private SpriteBatch batch;
	private Texture img;
	
	Screen MENU, GAME, PAUSE;
	
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
