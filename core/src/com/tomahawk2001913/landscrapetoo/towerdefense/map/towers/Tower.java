package com.tomahawk2001913.landscrapetoo.towerdefense.map.towers;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TopTile;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;

public abstract class Tower implements TopTile {
	public enum AimModes {
		NEAREST, FIRST, STRONGEST, WEAKEST;
	}
	
	private TextureRegion idleTextureRegion, shootingTextureRegion;
	private Animation shootingAnimation;
	private Vector2 location;
	private TileMap tm;
	private Entity target;
	
	private AimModes aimMode;
	
	private float rotation, range, fireRate, time, shootTime;
	
	private boolean isShooting;
	
	private static float originValue;
	
	// Constants
	public static final float DEFAULT_ROTATION = 90.0f, FLICKER_FIRERATE = 0.1f;
	
	public Tower(Vector2 location, float rotation, float range, float fireRate, TextureRegion idle, TextureRegion shooting, TileMap tm) {
		this.location = location;
		this.rotation = rotation;
		this.range = range;
		this.fireRate = fireRate;
		idleTextureRegion = idle;
		shootingTextureRegion = shooting;
		this.tm = tm;
		
		aimMode = AimModes.NEAREST;
		
		originValue = TileMap.TILE_DIMENSION / 2;
		time = 0;
		
		if(fireRate <= FLICKER_FIRERATE) {
			shootingAnimation = new Animation(fireRate, new TextureRegion[] {idleTextureRegion, shootingTextureRegion});
			shootingAnimation.setPlayMode(PlayMode.LOOP);
		} else shootingAnimation = null;
	}
	
	@Override
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		if(isShooting) {
			if(shootingAnimation == null) batch.draw(shootingTextureRegion, location.x + xOffset, location.y + yOffset, originValue, originValue, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION, 1, 1, rotation);
			else batch.draw(shootingAnimation.getKeyFrame(time), location.x + xOffset, location.y + yOffset, originValue, originValue, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION, 1, 1, rotation);
		} else {
			batch.draw(idleTextureRegion, location.x + xOffset, location.y + yOffset, originValue, originValue, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION, 1, 1, rotation);
		}
	}
	
	@Override
	public void update(float delta) {
		time += delta;
		
		isShooting = false;
		if(target != null) {
			shootTime += delta;
			rotation = aimAngle(target, 1);
			if(shootTime >= fireRate) {
				isShooting = true;
				shoot(target, delta);
				shootTime -= fireRate;
			}
		}
		
		findTarget();
	}
	
	public void findTarget() {
		target = null;
		List<Entity> entities = tm.getEntities();
		
		Entity check = null;
		for(Entity entity : entities) {
			if(!entity.isHostile()) continue;
			
			if(check == null) {
				if(targetWithinRange(entity)) {
					check = entity;
				}
				continue;
			}
			
			switch(aimMode) {
			case NEAREST:
				if(tm.getDistance(check.getLocation(), location) > tm.getDistance(entity.getLocation(), location))
					check = entity;
				break;
			case FIRST:
				break;
			case STRONGEST:
				break;
			case WEAKEST:
				break;
				
			}
		}
		
		target = check;
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
	
	public void setTileMap(TileMap tm) {
		this.tm = tm;
	}
	
	public void setLocation(float x, float y) {
		location.set(x, y);
	}
	
	public void setAimMode(AimModes aimMode) {
		this.aimMode = aimMode;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public float getFireRate() {
		return fireRate;
	}
	
	public Vector2 getLocation() {
		return location;
	}
	
	public TileMap getTileMap() {
		return tm;
	}
	
	public abstract void shoot(Entity target, float delta);
	
	public abstract float getDamage();
	
	public abstract int getPrice();
	
	public abstract String getName();
	
	public abstract Tower copy();
}
