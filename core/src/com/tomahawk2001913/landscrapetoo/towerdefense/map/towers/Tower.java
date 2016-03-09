package com.tomahawk2001913.landscrapetoo.towerdefense.map.towers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TopTile;

public class Tower implements TopTile {
	private TextureRegion idleTextureRegion, shootingTextureRegion;
	private Vector2 location;
	private TileMap tm;
	
	private float rotation;
	
	private boolean isShooting;
	
	private static float originValue;
	
	// Constants
	public static final float DEFAULT_ROTATION = 90;
	
	public Tower(Vector2 location, float rotation, TextureRegion idle, TextureRegion shooting, TileMap tm) {
		this.location = location;
		this.rotation = rotation;
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
			batch.draw(idleTextureRegion, location.x *TileMap.TILE_DIMENSION + xOffset, location.y * TileMap.TILE_DIMENSION + yOffset, originValue, originValue, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION, 1, 1, rotation);
		}
	}
	
	@Override
	public void update(float delta) {
		rotation += 50 * delta;
	}
	
	public void aimNearestTarget() {
		
	}
}
