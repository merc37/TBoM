package nega.tbom.screens;

import nega.tbom.Floor;
import nega.tbom.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {

	private final MainGame MAIN_GAME;
	private long currTime, prevTime;
	private float accumulator, frameTime, time;
	private final float dt = 1.0f/144.0f;
	
	private Floor floor;
	
	public GameScreen(final MainGame game) {
		MAIN_GAME = game;
		prevTime = TimeUtils.millis();
		floor = new Floor();
		System.out.println(dt);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.setTitle(""+Gdx.graphics.getFramesPerSecond());
		
		//update
		currTime = TimeUtils.millis();
		frameTime = currTime - prevTime;
		frameTime = (float)(frameTime / 1000.0f);
		prevTime = currTime;
		System.out.print("Before: " + frameTime);
		if(frameTime > 0.25f) {
			frameTime = 0.25f;
		}
		System.out.print(", After: " + frameTime);
		
		accumulator += frameTime;
		while(accumulator >= dt) {
			//do updating of game stuff
			floor.getCurrentLevel().update(dt, time);
			time += dt;
			accumulator -= dt;
		}
		
		//do rendering of game stuff and pass interpolation alpha gotten by accumulator/deltaTime
		floor.getCurrentLevel().render(time, accumulator/dt);
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
