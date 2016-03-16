package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.HealthBar;

public class Entity {
	private Vector2 location;
	private Vector2 velocity;
	private TileMap tm;
	private HealthBar hb;
	
	private List<Vector2> path;
	
	private int pathSpot;
	
	private float width, height, speed, health, alpha, time, flickerTime;
	
	// Constants
	public static final float DEFAULT_ENTITY_DIMENSION = 30, FLICKER_INTERVAL = 0.1f, DAMAGE_ALPHA = 0.5f;
	
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
		
		pathSpot = 1;
	}
	
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		hb.render(batch, xOffset, yOffset);
	}
	
	public void update(float delta) {
		if(health <= 0) die();
		time += delta;
		flickerTime -= delta;
		
		if(flickerTime > 0) {
			alpha = DAMAGE_ALPHA;
		} else {
			alpha = 1;
		}
		
		velocity.set(0, 0);
		
		if(path != null && path.size() > 0 && pathSpot < path.size()) {
			velocity.x = speed * Math.signum(path.get(pathSpot).x - location.x);
			velocity.y = speed * Math.signum(path.get(pathSpot).y - location.y);
			
			if(tm.getDistance(path.get(pathSpot), location) < 0.1f) 
				pathSpot++;
		}
		
		location.add(velocity.x * delta, velocity.y * delta);
	}
	
	public void setPath(List<Vector2> path) {
		this.path = path;
	}
	
	public void damage(float damage) {
		health -= damage;
		
		if(flickerTime <= -FLICKER_INTERVAL) flickerTime = FLICKER_INTERVAL;
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
	
	public Vector2 getVelocity() {
		return velocity;
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