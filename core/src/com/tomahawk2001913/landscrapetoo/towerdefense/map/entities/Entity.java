package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public abstract class Entity {
	private Vector2 location;
	private TileMap tm;
	
	private float width, height;
	
	// Constants
	public static final float DEFAULT_ENTITY_DIMENSION = 30;
	
	public Entity(Vector2 location, float width, float height, TileMap tm) {
		this.location = location;
		this.width = width;
		this.height = height;
		this.tm = tm;
	}
	
	public abstract void render(SpriteBatch batch, float xOffset, float yOffset);
	
	public abstract void update(float delta);
	
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