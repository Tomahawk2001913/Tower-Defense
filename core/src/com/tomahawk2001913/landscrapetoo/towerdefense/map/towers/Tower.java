package com.tomahawk2001913.landscrapetoo.towerdefense.map.towers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TopTile;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;

public class Tower implements TopTile {
	public enum AimModes {
		NEAREST, FIRST, STRONGEST;
	}
	
	private TextureRegion idleTextureRegion, shootingTextureRegion;
	private Vector2 location;
	private TileMap tm;
	private Entity target;
	
	private float rotation, range, damage;
	
	private boolean isShooting;
	
	private static float originValue;
	
	// Constants
	public static final float DEFAULT_ROTATION = 90;
	
	public Tower(Vector2 location, float rotation, float range, float damage, TextureRegion idle, TextureRegion shooting, TileMap tm) {
		this.location = location;
		this.rotation = rotation;
		this.range = range;
		this.damage = damage;
		idleTextureRegion = idle;
		shootingTextureRegion = shooting;
		this.tm = tm;
		
		originValue = TileMap.TILE_DIMENSION / 2;
	}
	
	@Override
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		if(isShooting) {
			batch.draw(shootingTextureRegion, location.x * TileMap.TILE_DIMENSION + xOffset, location.y * TileMap.TILE_DIMENSION + yOffset, originValue, originValue, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION, 1, 1, rotation);
		} else {
			batch.draw(idleTextureRegion, location.x * TileMap.TILE_DIMENSION + xOffset, location.y * TileMap.TILE_DIMENSION + yOffset, originValue, originValue, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION, 1, 1, rotation);
		}
	}
	
	@Override
	public void update(float delta) {
		isShooting = false;
		
		findNearestTarget();
		if(target != null) rotation = aimAngle(target, 1);
		
		if(target != null) {
			isShooting = true;
			target.damage(damage * delta);
		}
	}
	
	public void findNearestTarget() {
		target = null;
		List<Entity> entities = tm.getEntities();
		
		Entity nearest = null;
		for(Entity entity : entities) {
			if(nearest == null) {
				if(targetWithinRange(entity)) {
					nearest = entity;
				}
				continue;
			}
			
			if(tm.getDistance(nearest.getLocation(), location) > tm.getDistance(entity.getLocation(), location))
				nearest = entity;
		}
		
		target = nearest;
	}
	
	public float aimAngle(Entity target, float bulletSpeed) {
		// place holder velocity variables until I add smooth transitions between tiles
		float targetVX = 0;
		float targetVY = 0;
		
		float targetX = target.getLocation().x - location.x, targetY = target.getLocation().y - location.y;
		
		float rCrossV = targetX * targetVX - targetY * targetVY;
		float magR = (float) Math.sqrt(targetX * targetX + targetY * targetY);
		float angleAdjust = (float) Math.asin(rCrossV / (bulletSpeed * magR));
		
		return (float) Math.toDegrees((angleAdjust + Math.atan2(targetY, targetX)));
		
		//return (float) Math.toDegrees(Math.atan2(target.getLocation().y - location.y, target.getLocation().x - location.x));
	}
	
	public boolean targetWithinRange(Entity entity) {
		if(tm.getDistance(entity.getLocation(), location) <= range) return true;
		return false;
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
