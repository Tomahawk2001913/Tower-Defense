package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public abstract class Entity {
	private Vector2 location;
	private Vector2 velocity;
	private TileMap tm;
	
	private List<Vector2> path;
	
	private float width, height, speed;
	private float tileTransitionTime;
	
	// Constants
	public static final float DEFAULT_ENTITY_DIMENSION = 30;
	
	public Entity(Vector2 location, float width, float height, float speed, TileMap tm, List<Vector2> path) {
		this.location = location;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.tm = tm;
		
		velocity = new Vector2();
		this.path = path;
		
		tileTransitionTime = 0;
	}
	
	public abstract void render(SpriteBatch batch, float xOffset, float yOffset);
	
	public void update(float delta) {
		location.add(velocity);
		
		if(path.size() > 0) {
			if(tileTransitionTime >= speed) {
				tileTransitionTime -= speed;
				if(path.get(0) != null) {
					location.set(path.get(0));
					path.remove(0);
				}
			}
			
			tileTransitionTime += delta;
		}
	}
	
	public void addPath(List<Vector2> path) {
		this.path = path;
		tileTransitionTime = 0;
	}
	
	public Vector2 getLocation() {
		return location;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setLocation(float x, float y) {
		location.set(x, y);
	}
	
	public TileMap getTileMap() {
		return tm;
	}
}