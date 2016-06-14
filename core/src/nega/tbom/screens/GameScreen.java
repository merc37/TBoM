package nega.tbom.screens;

import nega.tbom.Floor;
import nega.tbom.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

	private final MainGame MAIN_GAME;
	private float accumulator, frameTime, time;
	private final float dt = 1.0f/60.0f;
	
	private Floor currFloor;
	
	public GameScreen(final MainGame game) {
		MAIN_GAME = game;
		currFloor = new Floor();
		System.out.println(dt);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.setTitle(""+Gdx.graphics.getFramesPerSecond());
		
		frameTime = Gdx.graphics.getRawDeltaTime();
		
		if(frameTime > 0.25) {
			frameTime = 0.25f;
		}
		System.out.print(", Frametime: " + frameTime);
		System.out.print(", accum: "+accumulator);
		
		accumulator += frameTime;
		while(accumulator >= dt) {
			//do updating of game stuff
			currFloor.getCurrentLevel().update(dt, time);
			time += dt;
			accumulator -= dt;
		}
		
		//do rendering of game stuff and pass interpolation alpha gotten by accumulator/dt
		currFloor.getCurrentLevel().render(time, accumulator/dt);
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
		currFloor.dispose();
	}
}
