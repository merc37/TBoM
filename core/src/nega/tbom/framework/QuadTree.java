package nega.tbom.framework;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class QuadTree {
	
	private int MAX_OBJECTS = 7;
	
	private QuadTree[] nodes;
	private LinkedList<Rectangle> rects;
	private Rectangle bounds;
	
	public QuadTree(Rectangle bounds){
		nodes = new QuadTree[4];
		this.bounds = bounds;
		rects = new LinkedList<Rectangle>();
	}
	
	public void insert(Rectangle obj){
		if(nodes[0] != null){
			if(obj.overlaps(nodes[0].bounds)){
				nodes[0].insert(obj);
			}
			
			if(obj.overlaps(nodes[1].bounds)){
				nodes[1].insert(obj);	
			}
			
			if(obj.overlaps(nodes[2].bounds)){
				nodes[2].insert(obj);
			}
			
			if(obj.overlaps(nodes[3].bounds)){
				nodes[3].insert(obj);
			}
			
			return;
		}
		
		rects.add(obj);
		
		if(rects.size() > MAX_OBJECTS && nodes[0] == null){
			split();
			
			Rectangle tmp;
			for(int i = 0; i<rects.size(); i++){
				tmp = rects.get(i);
				if(tmp.overlaps(nodes[0].bounds)){
					nodes[0].insert(tmp);
				}
				
				if(tmp.overlaps(nodes[1].bounds)){
					nodes[1].insert(tmp);	
				}
				
				if(tmp.overlaps(nodes[2].bounds)){
					nodes[2].insert(tmp);
				}
				
				if(tmp.overlaps(nodes[3].bounds)){
					nodes[3].insert(tmp);
				}
			}
			rects.clear();
		}
	}
	
	public LinkedList<Rectangle> retrieve(Rectangle obj, LinkedList<Rectangle> possRects){
		if(nodes[0] != null){
			if(obj.overlaps(nodes[0].bounds)){
				possRects.addAll(nodes[0].retrieve(obj, possRects));
			}
			
			if(obj.overlaps(nodes[1].bounds)){
				possRects.addAll(nodes[1].retrieve(obj, possRects));
			}
			
			if(obj.overlaps(nodes[2].bounds)){
				possRects.addAll(nodes[2].retrieve(obj, possRects));
			}
			
			if(obj.overlaps(nodes[3].bounds)){
				possRects.addAll(nodes[3].retrieve(obj, possRects));
			}
		} else {
			possRects.addAll(rects);
		}
		
		return possRects;
	}
	
	private void split(){
		if(nodes[0] == null){
			float x = bounds.getX(), y = bounds.getY(), w = bounds.getWidth()/2, h = bounds.getHeight()/2;//half width and height
			nodes[0] = new QuadTree(new Rectangle(x, y, w, h));
			nodes[1] = new QuadTree(new Rectangle(x+w, y, w, h));
			nodes[2] = new QuadTree(new Rectangle(x, y+h, w, h));
			nodes[3] = new QuadTree(new Rectangle(x+w, y+h, w, h));
		}
	}
	
	public void clearAll(){
		rects.clear();
		
		for(int i = 0; i<nodes.length; i++){
			if(nodes[i] != null){
				nodes[i].clearAll();
				nodes[i] = null;
			}
		}
	}
	
	@Override
	public String toString(){
		return "Bounds: " + bounds.getX() + ", " + bounds.getY() + ", " + bounds.getWidth() + ", " + bounds.getHeight() + ", ";
	}
	
	public void render(ShapeRenderer sr){//to visualize, not part of actual class
		if(nodes[0] != null){
			nodes[0].render(sr);
			nodes[1].render(sr);
			nodes[2].render(sr);
			nodes[3].render(sr);
		}
		
		sr.set(ShapeRenderer.ShapeType.Line);
		sr.setColor(Color.BLACK);
		sr.rect(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
		sr.set(ShapeRenderer.ShapeType.Filled);
		sr.setColor(Color.RED);
		for(int i = 0; i<rects.size(); i++){
			sr.rect(rects.get(i).getX(), rects.get(i).getY(), rects.get(i).getWidth(), rects.get(i).getHeight());
		}
	}
	
}
