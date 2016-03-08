package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Tower implements TopTile {
	private TextureRegion img;
	private Vector2 location;
	
	private float rotation;
	
	private static float originValue;
	
	// Constants
	public static final float DEFAULT_ROTATION = 180;
	
	public Tower(Vector2 location, float rotation, TextureRegion img) {
		this.location = location;
		this.rotation = rotation;
		this.img = img;
		
		originValue = TileMap.TILE_DIMENSION / 2;
	}
	
	@Override
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		batch.draw(img, location.x, location.y, originValue, originValue, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION, 0, 0, rotation);
	}
	
	@Override
	public void update(float delta) {
		
	}
}
