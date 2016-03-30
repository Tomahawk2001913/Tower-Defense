package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public abstract class Bullet extends Entity {
	private TextureRegion img;
	
	private float rotation;
	private static float origin;
	
	public Bullet(Vector2 location, float speed, float rotation, float health, float damage, boolean hostile, TileMap tm, TextureRegion img) {
		super(location, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION, speed, 1, damage, hostile, tm, null);
		this.rotation = rotation;
		origin = TileMap.TILE_DIMENSION / 2;
		
		this.img = img;
		
		getVelocity().set((float) Math.cos(Math.toRadians(rotation)) * getSpeed(), (float) Math.sin(Math.toRadians(rotation)) * getSpeed());
	}
	
	@Override
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		batch.draw(img, getLocation().x + xOffset, getLocation().y + yOffset, origin, origin, getWidth(), getHeight(), 1, 1, rotation);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		for(Entity entity : getTileMap().getEntities()) {
			if(getBounds().overlaps(entity.getBounds()) && !(entity instanceof Bullet)) {
				hit(entity);
				getTileMap().removeEntity(this);
				break;
			}
		}
	}
	
	public abstract void hit(Entity entity);
}