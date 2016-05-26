package nega.tbom.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import nega.tbom.baseobjects.Entity;

public class Player extends Entity{

	public Player(Rectangle rect, Texture texture) {
		super(rect, texture);
	}
	
	@Override
	public void update(float delta, float time){
		super.update(delta, time);
		
	}
	
	@Override
	public void render(SpriteBatch batch, float time, float alpha){
		super.render(batch, time, alpha);
	}
}
