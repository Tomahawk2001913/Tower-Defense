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
	
	private boolean modifyX, modifyY;
	
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
		modifyX = false;
		modifyY = false;
	}
	
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		hb.render(batch, xOffset, yOffset);
	}
	
	public void update(float delta) {
		delta /= 5;
		if(health <= 0) die();
		time += delta;
		flickerTime -= delta;
		
		if(flickerTime > 0) {
			alpha = DAMAGE_ALPHA;
		} else {
			alpha = 1;
		}
		
		float currentXSignum = 0;
		float currentYSignum = 0;
		
		velocity.set(0, 0);
		
		if(path != null && path.size() > 0 && pathSpot < path.size()) {
			currentXSignum = -Math.signum(location.x - path.get(pathSpot).x);
			currentYSignum = -Math.signum(location.y - path.get(pathSpot).y);
			
			velocity.x = speed * currentXSignum;
			velocity.y = speed * currentYSignum;
			
			
			if(path.get(pathSpot).x != path.get(pathSpot - 1).x) modifyX = true;
			else if(path.get(pathSpot).y != path.get(pathSpot - 1).y) {
				modifyX = false;
				modifyY = true;
			}
			
			System.out.println(modifyX + " " + modifyY);
			
			float newX = location.x + velocity.x * delta, newY = location.y + velocity.y * delta;
			
			if(pathSpot < path.size() && (currentXSignum != 0 || currentYSignum != 0)) {
				if(currentXSignum != 0 && currentXSignum != -Math.signum(newX - path.get(pathSpot).x)) {
					pathSpot++;
				} else if(currentYSignum != 0 && currentYSignum != -Math.signum(newY - path.get(pathSpot).y)) {
					pathSpot++;
				}
			}
			
			if(!modifyX) newX = location.x;
			if(!modifyY) newY = location.y;
			
			location.set(newX, newY);
		}
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