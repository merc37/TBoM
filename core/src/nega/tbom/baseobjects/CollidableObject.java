package nega.tbom.baseobjects;

import nega.tbom.objects.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class CollidableObject extends GameObject implements Collision {
	
	public CollidableObject(Rectangle rect, Texture texture) {
		super(rect, texture);
	}

	public abstract void update(float delta, float time);
	
	public abstract void render(SpriteBatch batch, float time, float alpha);
	
	public boolean overlaps(Rectangle r) {
		return rect.overlaps(r);
	}
	
	public Rectangle getRect() {
		return rect;
	}

	//Double Dispatch Collision
	//Add methods for objects as needed
	//Doctrine: only the class its in takes care of itself (one) each its own
	public boolean onCollide(CollidableObject obj) {
		return false;
	}
	
	public boolean onCollide(Player obj) {
		return false;
	}
}
