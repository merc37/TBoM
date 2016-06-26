package nega.tbom;

import nega.tbom.objects.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;

public class Floor {
	
	private AssetManager asm;
	
	private Level currLevel;
	
	public Floor(AssetManager asm){
		this.asm = asm;
		currLevel = new Level(new Player(new Rectangle(0, 0, 32, 32)),asm.get("testmap.tmx", TiledMap.class));
		Gdx.input.setInputProcessor(currLevel);
	}
	
	public Level getCurrentLevel(){
		return currLevel;
	}
}
