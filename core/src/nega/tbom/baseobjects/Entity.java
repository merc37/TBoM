package nega.tbom.baseobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity extends CollidableObject {

	protected float renderX, renderY;
	
	public Entity(Rectangle rect, Texture texture) {
		super(rect, texture);
	}

	@Override
	public void update(float delta, float time) {
		renderX = getX();
		renderY = getY();
	}
	
	@Override
	public void render(SpriteBatch batch, float time, float alpha) {
		renderX = getX()*alpha + renderX*(1.0f-alpha);
		renderY = getY()*alpha + renderY*(1.0f-alpha);
	}
}
