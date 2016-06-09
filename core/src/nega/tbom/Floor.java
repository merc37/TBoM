package nega.tbom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;

import nega.tbom.objects.Player;

public class Floor {
	
	private Level currLevel = new Level(new Player(new Rectangle(0, 0, 32, 32), new Texture("rsplayer.jpg")), new TmxMapLoader().load("testmap.tmx"));
	
	public Floor(){
		Gdx.input.setInputProcessor(currLevel);
	}
	
	public Level getCurrentLevel(){
		return currLevel;
	}
}
