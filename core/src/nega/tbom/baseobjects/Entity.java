package nega.tbom.baseobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends CollidableObject {

	protected Vector2 direction;
	protected float speed;
	protected boolean moving;
	protected float renderX, renderY;
	
	public Entity(Rectangle rect, Texture texture) {
		super(rect, texture);
		direction = new Vector2(0, 0);
		speed = 375;
		moving = false;
	}

	@Override
	public void update(float delta, float time) {
		if(moving){
			renderX = getX();
			renderY = getY();
		}
	}
	
	@Override
	public void render(SpriteBatch batch, float time, float alpha) {
		if(moving){
			renderX = getX()*alpha + renderX*(1.0f-alpha);
			renderY = getY()*alpha + renderY*(1.0f-alpha);
		}
	}
	
	public float getRenderX(){
		return renderX;
	}
	
	public float getRenderY(){
		return renderY;
	}
	
	public void setMoving(boolean moving){
		this.moving = moving;
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
