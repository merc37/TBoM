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
		super.render();
	}
}
