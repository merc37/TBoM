package nega.tbom.objects;

import nega.tbom.baseobjects.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Entity {

	public static Texture playerTexture;
	
	public Player(Rectangle rect) {
		super(rect);
	}
	
	@Override
	public void update(float delta, float time) {
		super.update(delta, time);
			
		System.out.print(", PrevX: " + renderX); 
		setX(getX() + direction.x*speed*delta);
		System.out.print(", NewX: " + getX());
		setY(getY() + direction.y*speed*delta);
	}
	
	@Override
	public void render(SpriteBatch batch, float time, float alpha) {
		super.render(batch, time, alpha);
			
		System.out.print(", InterpX: " + renderX + ", ");
		batch.draw(playerTexture, renderX, renderY);
	}
}
