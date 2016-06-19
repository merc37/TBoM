package nega.tbom.baseobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	
	protected Rectangle rect;
	protected Texture texture;
	protected float prevX, prevY;
	
	public GameObject(Rectangle rect, Texture texture) {
		this.rect = rect;
		this.texture = texture;
	}
	
	public abstract void update(float delta, float time);
	
	public abstract void render(SpriteBatch batch, float time, float alpha);
	
	public float getX() {
		return rect.getX();
	}
	
	public float getY() {
		return rect.getY();
	}
	
	public float getWidth() {
		return rect.getWidth();
	}
	
	public float getHeight() {
		return rect.getHeight();
	}
	
	public Vector2 getPos(Vector2 pos) {
		return rect.getPosition(pos);
	}
	
	public Vector2 getCenter(Vector2 center){
		return rect.getCenter(center);
	}
	
	public void setX(float x) {
		rect.setX(x);
	}
	
	public void setY(float y) {
		rect.setY(y);
	}
	
	public void setWidth(float width) {
		rect.setWidth(width);
	}
	
	public void setHeight(float height) {
		rect.setHeight(height);
	}
	
	public void setPos(Vector2 position) {
		rect.setPosition(position);
	}
	
	public static Animation makeAnimation(Texture sheet, int columns, int rows) {//assumes pixel squares for each frame
		TextureRegion[][] regions = TextureRegion.split(sheet, sheet.getWidth()/columns, sheet.getHeight()/rows);
		TextureRegion[] frames = new TextureRegion[columns*rows];
		
		int index = 0;
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j < columns; j++) {
                frames[index++] = regions[i][j];
            }
        }
        
        return new Animation(0.0166f, frames);
	}
}
