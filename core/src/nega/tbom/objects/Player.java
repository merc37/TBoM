package nega.tbom.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import nega.tbom.baseobjects.Entity;

public class Player extends Entity {
	
	private Vector2 direction;
	private float speed;

	public Player(Rectangle rect, Texture texture) {
		super(rect, texture);
		direction = new Vector2(0, 0);
		speed = 10f;
	}
	
	@Override
	public void update(float delta, float time) {
		super.update(delta, time);
		setX(getX() + direction.x*speed);
		setY(getY() + direction.y*speed);
	}
	
	@Override
	public void render(SpriteBatch batch, float time, float alpha) {
		super.render(batch, time, alpha);
	}
	
	/**
	 * Sets the X direction of player movement
	 * @param x 
	 * @precondition input must be -1, 0, or 1
	 */
	public void setDirX(int x) {
		direction.x = x;
	}
	
	/**
	 * Sets the Y direction of player movement
	 * @param y 
	 * @precondition input must be -1, 0, or 1
	 */
	public void setDirY(int y) {
		direction.y = y;
	}
}
