package nega.tbom.screens;

import nega.tbom.Floor;
import nega.tbom.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

public class GameScreen implements Screen {

	private final MainGame MAIN_GAME;
	private float accumulator, frameTime, time, prevDelta;
	private final float dt = 1.0f/60.0f;
	
	private Floor currFloor;
	private AssetManager asm;
	
	public GameScreen(final MainGame game, AssetManager asm) {
		MAIN_GAME = game;
		this.asm = asm;
		currFloor = new Floor(asm);
		prevDelta = Gdx.graphics.getRawDeltaTime();
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		//Gdx.graphics.setTitle(""+Gdx.graphics.getFramesPerSecond());
		
		/*prevDelta = frameTime;
		frameTime = Gdx.graphics.getRawDeltaTime();
		if((Math.abs(frameTime-prevDelta)) > 2.0f){
			frameTime = prevDelta;
		}*/
		prevDelta = Gdx.graphics.getRawDeltaTime();
		System.out.print((prevDelta*1000f) + ", ");
		if((prevDelta*1000f)>5.0f){
			System.out.print("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
					+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
		}
		System.out.println();
		frameTime = Gdx.graphics.getRawDeltaTime();
		if(frameTime > 0.25) {
			frameTime = 0.25f;
		}
		//System.out.print(", Frametime: " + frameTime*1000f);
		//System.out.print(", accum: "+accumulator);
		
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
		asm.dispose();
	}
}
