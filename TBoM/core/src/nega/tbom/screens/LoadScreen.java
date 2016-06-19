package nega.tbom.screens;

import nega.tbom.MainGame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class LoadScreen implements Screen{

	private AssetManager asm;
	private ShapeRenderer sr;
	private final MainGame MAIN_GAME;
	
	public LoadScreen(final MainGame game){
		MAIN_GAME = game;
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);
		asm = new AssetManager();
		loadAllAssets();
	}
	
	@Override
	public void show() {
		
	}

	private void loadAllAssets(){
		asm.load("rsplayer.jpg", Texture.class);
		asm.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		asm.load("testmap.tmx", TiledMap.class);
	}
	
	@Override
	public void render(float delta) {
		if(asm.update()){
			MAIN_GAME.setScreen(new GameScreen(MAIN_GAME, asm));
		} else {
			sr.begin();
			sr.set(ShapeType.Filled);
			sr.rect(0, 0, asm.getProgress()*256, 64);
			sr.end();
		}
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
