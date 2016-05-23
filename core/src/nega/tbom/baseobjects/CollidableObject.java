package nega.tbom.baseobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class CollidableObject extends GameObject{

	public CollidableObject(Rectangle rect, Texture texture) {
		super(rect, texture);
	}
	
	public boolean overlaps(Rectangle r){
		return rect.overlaps(r);
	}

}
