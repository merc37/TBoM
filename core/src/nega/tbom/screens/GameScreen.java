package nega.tbom.screens;

import nega.tbom.Floor;
import nega.tbom.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {

	private final MainGame MAIN_GAME;
	private SpriteBatch batch;
	private long currTime, prevTime;
	private float accumulator, deltaTime = 0.01f, frameTime, time;
	private Floor floor;
	
	public GameScreen(final MainGame game) {
		MAIN_GAME = game;
		batch = new SpriteBatch();
		prevTime = TimeUtils.millis()-2;
		floor = new Floor();
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		//update
		currTime = TimeUtils.millis();
		frameTime = currTime-prevTime;
		frameTime = (float)(frameTime/1000.0f);
		time+=frameTime;
		prevTime = currTime;
		
		accumulator += frameTime;
		while(accumulator >= deltaTime) {
			//do updating of game stuff
			floor.getCurrentLevel().update(delta, time);
			accumulator -= deltaTime;
		}
		
		batch.begin();
		//do rendering of game stuff and pass interpolation alpha gotten by accumulator/deltaTime
		floor.getCurrentLevel().render(batch, time, accumulator/deltaTime);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
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
		
	}
}
