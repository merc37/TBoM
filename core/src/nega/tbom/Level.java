package nega.tbom;

import java.util.ArrayList;

import nega.tbom.baseobjects.CollidableObject;
import nega.tbom.baseobjects.GameObject;
import nega.tbom.framework.QuadTree;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Level extends GameObject{

	private QuadTree quadTree;
	private static ArrayList<CollidableObject> collidables = new ArrayList<CollidableObject>(25);
	private static ArrayList<GameObject> objects = new ArrayList<GameObject>(10);
	
	public Level(Rectangle rect, Texture texture) {
		super(rect, texture);
		quadTree = new QuadTree(rect);
	}
	
	@Override
	public void update(float delta, float time){
		for(int i = 0; i<objects.size(); i++){
			objects.get(i).update(delta, time);
		}
		
		for(int i = 0; i<collidables.size(); i++){
			collidables.get(i).update(delta, time);
		}
		
		quadTree.clearAll();
		for(int i = 0; i<collidables.size(); i++){
			quadTree.insert(collidables.get(i));
		}
		
		ArrayList<CollidableObject> possibleCollisions = new ArrayList<CollidableObject>(7);//possible error with how retrieve works
		ArrayList<Integer> removed = new ArrayList<Integer>();
		CollidableObject obj1, obj2;
		for(int i = 0; i<collidables.size(); i++){
			obj1 = collidables.get(i);
			possibleCollisions.clear();
			quadTree.retrieve(obj1, possibleCollisions);
			for(int j = 0; j<possibleCollisions.size(); j++){
				obj2 = possibleCollisions.get(j);
				if(obj1.overlaps(obj2.getRect())){
					boolean r = obj2.onCollide(obj1);
					if(r){
						removed.add(i);
					}
				}
			}
		}
		
		//remove objects
		int l;
		CollidableObject last;
		for(int i : removed){
			l = collidables.size()-1;
			last = collidables.get(l);
			while(removed.get(removed.size()-1)==l){
				collidables.remove(l);
				removed.remove(removed.size()-1);
				l = collidables.size()-1;
			}
			if(collidables.size()>=2){
				collidables.set(i, last);
				collidables.remove(l);
			}
		}
	}
	
	@Override
	public void render(SpriteBatch batch, float time, float alpha){
		
	}
	
	public static void AddObject(CollidableObject obj){
		collidables.add(obj);
	}
	
	public static void AddObject(GameObject obj){
		objects.add(obj);
	}
}