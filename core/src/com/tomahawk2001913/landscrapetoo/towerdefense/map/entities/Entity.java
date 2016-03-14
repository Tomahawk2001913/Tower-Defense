package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.HealthBar;

public class Entity extends Sprite {
	private Vector2 location;
	private Vector2 velocity;
	private TileMap tm;
	private HealthBar hb;
	
	private List<Vector2> path;
	
	private float width, height, speed, health, alpha, time, flickerTime;
	private float tileTransitionTime;
	
	// Constants
	public static final float DEFAULT_ENTITY_DIMENSION = 30;
	
	public Entity(Vector2 location, float width, float height, float speed, float health, TileMap tm, List<Vector2> path) {
		this.location = location;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.health = health;
		this.tm = tm;
		
		velocity = new Vector2();
		this.path = path;
		
		alpha = 1;
		time = 0;
		flickerTime = -0.5f;
		
		hb = new HealthBar(this);
		
		tileTransitionTime = 0;
	}
	
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		hb.render(batch, xOffset, yOffset);
	}
	
	public void update(float delta) {
		time += delta;
		flickerTime -= delta;
		
		if(flickerTime > 0) {
			alpha = 0.5f;
		} else {
			alpha = 1;
		}
		location.add(velocity);
		
		if(path != null && path.size() > 0) {
			if(tileTransitionTime >= speed) {
				tileTransitionTime -= speed;
				if(path.get(0) != null) {
					location.set(path.get(0));
					path.remove(0);
				}
			}
			
			tileTransitionTime += delta;
		}
		
		if(health <= 0) die();
	}
	
	public void setPath(List<Vector2> path) {
		this.path = path;
		tileTransitionTime = 0;
	}
	
	public void damage(float damage) {
		health -= damage;
		alpha = 0.5f;
		if(flickerTime <= -0.5f) flickerTime = 0.5f;
	}
	
	public void die() {
		tm.removeEntity(this);
	}
	
	public float getHealth() {
		return health;
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
	
	public float getAlpha() {
		return alpha;
	}
	
	public float getTime() {
		return time;
	}
	
	public void setLocation(float x, float y) {
		location.set(x, y);
	}
	
	public TileMap getTileMap() {
		return tm;
	}
}