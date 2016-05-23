package nega.tbom.baseobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
	
	protected Rectangle rect;
	protected Texture texture;
	protected float prevX, prevY;
	
	public GameObject(Rectangle rect, Texture texture){
		this.rect = rect;
		this.texture = texture;
	}
	
	public void update(float delta, float time){
		
	}
	
	public void render(SpriteBatch batch, float time, float alpha){
		batch.draw(texture, getX(), getY());
	}
	
	public float getX(){
		return rect.getX();
	}
	
	public float getY(){
		return rect.getY();
	}
	
	public float getWidth(){
		return rect.getWidth();
	}
	
	public float getHeight(){
		return rect.getHeight();
	}
	
	public Vector2 getPos(Vector2 pos){
		return rect.getPosition(pos);
	}
	
	public void setX(float x){
		rect.setX(x);
	}
	
	public void setY(float y){
		rect.setY(y);
	}
	
	public void setWidth(float width){
		rect.setWidth(width);
	}
	
	public void setHeight(float height){
		rect.setHeight(height);
	}
	
	public void setPos(Vector2 position){
		rect.setPosition(position);
	}
}
