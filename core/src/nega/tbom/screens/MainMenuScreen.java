package nega.tbom.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import nega.tbom.MainGame;

public class MainMenuScreen implements Screen {

	private final MainGame MAIN_GAME;
	
	private Stage stage;
	private Skin uiSkin;
	
	public MainMenuScreen(final MainGame game) {
		MAIN_GAME = game;
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		uiSkin = new Skin(Gdx.files.internal("uiskin/uiskin.json"));
		
//		Stack table = new Stack();
		Table table = new Table();
		table.setFillParent(true);
		TextButton OPTIONS_BUTTON = new TextButton("Options", uiSkin);
		table.add(OPTIONS_BUTTON);
		TextButton PLAY_BUTTON = new TextButton("Play Game", uiSkin);
		table.add(PLAY_BUTTON);
		stage.addActor(table);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);//temp
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		//true re-centers camera
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
