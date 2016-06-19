package nega.tbom.framework;

import java.util.ArrayList;

import nega.tbom.baseobjects.CollidableObject;

import com.badlogic.gdx.math.Rectangle;

public class QuadTree {
	
	private final int MAX_OBJECTS = 7;
	private final int MAX_SPLITS = 10;
	
	private QuadTree[] nodes;
	private ArrayList<CollidableObject> objects;
	private Rectangle bounds;
	private int splitLevel;
	
	public QuadTree(int splitLevel, Rectangle bounds){
		nodes = new QuadTree[4];
		this.bounds = bounds;
		objects = new ArrayList<CollidableObject>();
		this.splitLevel = splitLevel;
	}
	
	public void insert(CollidableObject obj){
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
		
		objects.add(obj);
		
		if(objects.size() > MAX_OBJECTS && splitLevel<=MAX_SPLITS && nodes[0] == null){
			split();
			
			CollidableObject tmp;
			for(int i = 0; i<objects.size(); i++){
				tmp = objects.get(i);
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
			objects.clear();
		}
	}
	
	public ArrayList<CollidableObject> retrieve(CollidableObject obj, ArrayList<CollidableObject> possobjects){
		if(nodes[0] != null){
			if(obj.overlaps(nodes[0].bounds)){
				possobjects.addAll(nodes[0].retrieve(obj, possobjects));
			}
			
			if(obj.overlaps(nodes[1].bounds)){
				possobjects.addAll(nodes[1].retrieve(obj, possobjects));
			}
			
			if(obj.overlaps(nodes[2].bounds)){
				possobjects.addAll(nodes[2].retrieve(obj, possobjects));
			}
			
			if(obj.overlaps(nodes[3].bounds)){
				possobjects.addAll(nodes[3].retrieve(obj, possobjects));
			}
		} else {
			possobjects.addAll(objects);
		}
		
		return possobjects;
	}
	
	public void removeObject(CollidableObject obj, QuadTree parent){
		if(nodes[0] != null){
			if(obj.overlaps(nodes[0].bounds)){
				removeObject(obj, this);
			}
			
			if(obj.overlaps(nodes[1].bounds)){
				removeObject(obj, this);
			}
			
			if(obj.overlaps(nodes[2].bounds)){
				removeObject(obj, this);
			}
			
			if(obj.overlaps(nodes[3].bounds)){
				removeObject(obj, this);
			}
		}
		if(nodes[0] == null){
			objects.remove(obj);
			if(parent.nodes[0].objects.isEmpty() && parent.nodes[1].objects.isEmpty() &&
					parent.nodes[2].objects.isEmpty() && parent.nodes[3].objects.isEmpty()){
				parent.nodes[0] = null;
				parent.nodes[1] = null;
				parent.nodes[2] = null;
				parent.nodes[3] = null;
			}
		}
	}
	
	private void split(){
		if(nodes[0] == null){
			float x = bounds.getX(), y = bounds.getY(), w = bounds.getWidth()/2, h = bounds.getHeight()/2;//half width and height
			nodes[0] = new QuadTree(splitLevel+1, new Rectangle(x, y, w, h));
			nodes[1] = new QuadTree(splitLevel+1, new Rectangle(x+w, y, w, h));
			nodes[2] = new QuadTree(splitLevel+1, new Rectangle(x, y+h, w, h));
			nodes[3] = new QuadTree(splitLevel+1, new Rectangle(x+w, y+h, w, h));
		}
	}
	
	public void clearAll(){
		objects.clear();
		
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
}
