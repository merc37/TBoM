package nega.tbom;

import java.util.ArrayList;

import nega.tbom.baseobjects.CollidableObject;
import nega.tbom.baseobjects.GameObject;
import nega.tbom.framework.QuadTree;
import nega.tbom.objects.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Level implements InputProcessor {

	private QuadTree quadTree;
	private Player player;
	private OrthographicCamera cam;
	private OrthogonalTiledMapRenderer mapRenderer;
	private SpriteBatch batch;
	private int mapWidth, mapHeight, mapTileWidth, mapTileHeight;
	private static ArrayList<CollidableObject> collidables = new ArrayList<CollidableObject>(25);
	private static ArrayList<GameObject> objects = new ArrayList<GameObject>(10);
	
	public Level(Player player, TiledMap map) {
		this.player = player;
		
		batch = new SpriteBatch();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		MapProperties props = map.getProperties();
		mapWidth = props.get("width", Integer.class);
		mapHeight = props.get("height", Integer.class);
		mapTileWidth = props.get("tilewidth", Integer.class);
		mapTileHeight = props.get("tileheight", Integer.class);
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		
		quadTree = new QuadTree(0, new Rectangle(0, 0, mapWidth*mapTileWidth, mapHeight*mapTileHeight));
		
		AddObject(player);//temp
	}
	
	public void update(float delta, float time) {
		for(int i = 0; i<objects.size(); i++) {
			objects.get(i).update(delta, time);
		}
		
		for(int i = 0; i<collidables.size(); i++) {
			collidables.get(i).update(delta, time);
		}
		
		quadTree.clearAll();
		for(int i = 0; i<collidables.size(); i++) {
			quadTree.insert(collidables.get(i));
		}
		
		ArrayList<CollidableObject> possibleCollisions = new ArrayList<CollidableObject>(7);//possible error with how retrieve works
		ArrayList<Integer> removed = new ArrayList<Integer>();
		CollidableObject obj1, obj2;
		for(int i = 0; i<collidables.size(); i++) {
			obj1 = collidables.get(i);
			possibleCollisions.clear();
			quadTree.retrieve(obj1, possibleCollisions);
			for(int j = 0; j<possibleCollisions.size(); j++) {
				obj2 = possibleCollisions.get(j);
				if(obj1.overlaps(obj2.getRect())) {
					boolean remove = obj2.onCollide(obj1);
					if(remove) removed.add(i);
				}
			}
		}
		
		//remove objects
		if(!removed.isEmpty()) {
			int l;
			CollidableObject last;
			
			for(int i = removed.size()-1; i>=0; i--) {
				l = collidables.size()-1;
				last = collidables.get(l);
				collidables.set(removed.get(i), last);
				collidables.remove(l);
			}
		}
	}
	
	public void render(float time, float alpha) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(cam.combined);
		
		mapRenderer.setView(cam);
		
		mapRenderer.getBatch().disableBlending();
		mapRenderer.render();
		for (int i = 0; i < 500; i++) { //le fps throttler
			mapRenderer.render();
		}
		
		batch.begin();
		for(int i = 0; i<objects.size(); i++) {
			objects.get(i).render(batch, time, alpha);
		}
		
		for(int i = 0; i<collidables.size(); i++) {
			collidables.get(i).render(batch, time, alpha);
		}
		batch.end();
		
		/*cam.position.x = player.getRenderX() + player.getWidth()/2;
		cam.position.y = player.getRenderY() + player.getHeight()/2;
		cam.position.set(MathUtils.clamp(cam.position.x, 0, mapWidth*mapTileWidth),
				MathUtils.clamp(cam.position.y, 0, mapHeight*mapTileHeight), 0);
		cam.update();*/
	}
	
	public static void clearObjects() {
		objects.clear();
		collidables.clear();
	}
	
	public static void AddObject(CollidableObject obj) {
		collidables.add(obj);
	}
	
	public static void AddObject(GameObject obj) {
		objects.add(obj);
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.W:
			player.setDirY(1);
			break;
		case Keys.A:
			player.setDirX(-1);
			break;
		case Keys.S:
			player.setDirY(-1);
			break;
		case Keys.D:
			player.setDirX(1);
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.W:
			player.setDirY(0);
			break;
		case Keys.A:
			player.setDirX(0);
			break;
		case Keys.S:
			player.setDirY(0);
			break;
		case Keys.D:
			player.setDirX(0);
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}