package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.Playing;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.HealthBar;

public class Entity {
	private Vector2 location;
	private Vector2 velocity;
	private Rectangle bounds;
	private TileMap tm;
	private HealthBar hb;
	
	private List<Vector2> path;
	
	private int pathSpot, worth;
	
	private float width, height, speed, health, damage, alpha, time, flickerTime;
	
	private boolean isHostile;
	
	// Constants
	public static final float DEFAULT_ENTITY_DIMENSION = 30, FLICKER_INTERVAL = 0.1f, DAMAGE_ALPHA = 0.5f;
	
	public Entity(Vector2 location, float width, float height, float speed, float health, float damage, int worth, boolean hostile, TileMap tm, List<Vector2> path) {
		this.location = location;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.health = health;
		this.damage = damage;
		this.worth = worth;
		this.tm = tm;
		
		velocity = new Vector2();
		this.path = path;
		
		bounds = new Rectangle(location.x, location.y, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
		
		alpha = 1;
		time = 0;
		flickerTime = -DAMAGE_ALPHA;
		
		this.isHostile = hostile;
		
		hb = new HealthBar(this.health);
		
		pathSpot = 1;
	}
	
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		hb.render(batch, location.x + xOffset, location.y + yOffset + height);
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
		
		if(path != null && path.size() > 0 && pathSpot < path.size()) {
			velocity.x = speed * Math.signum(path.get(pathSpot).x - location.x);
			velocity.y = speed * Math.signum(path.get(pathSpot).y - location.y);
			
			if(tm.getDistance(path.get(pathSpot), location) < 0.4f) {
				pathSpot++;
				
				// Finishes path.
				if(pathSpot >= path.size()) {
					path = null;
					velocity.set(0, 0);
					return;
				}
				
				int spotTileX = (int) (path.get(pathSpot).x / TileMap.TILE_DIMENSION), spotTileY = (int) (path.get(pathSpot).y / TileMap.TILE_DIMENSION);
				
				if(tm.getTile(spotTileX, spotTileY).isSolid() ||
						(tm.getTopTile(spotTileX, spotTileY) != null && tm.getTopTile(spotTileX, spotTileY).isSolid())) {
					setPath(tm.findPath(new Vector2((int) (path.get(pathSpot - 1).x / TileMap.TILE_DIMENSION), (int) (path.get(pathSpot - 1).y / TileMap.TILE_DIMENSION)), 
							new Vector2(path.get(path.size() - 1).x / TileMap.TILE_DIMENSION, path.get(path.size() - 1).y / TileMap.TILE_DIMENSION)));
					pathSpot = 0;
				}
			}
		}
		
		location.add(velocity.x * delta, velocity.y * delta);
		
		bounds.set(location.x, location.y, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
		
		if(isHostile && bounds.overlaps(tm.getBase().getBounds())) {
			tm.getBase().damage(damage * delta);
		}
	}
	
	public void setPath(List<Vector2> path) {
		this.path = path;
	}
	
	public void damage(float damage) {
		health -= damage;
		
		hb.setCurrentHealth(health);
		
		if(flickerTime <= -FLICKER_INTERVAL) flickerTime = FLICKER_INTERVAL;
	}
	
	public void die() {
		Playing.addMoney(getWorth());
		
		tm.removeEntity(this);
	}
	
	public boolean isHostile() {
		return isHostile;
	}
	
	public float getHealth() {
		return health;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public float getDamage() {
		return damage;
	}
	
	public int getWorth() {
		return worth;
	}
	
	public Vector2 getLocation() {
		return location;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public Rectangle getBounds() {
		return bounds;
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