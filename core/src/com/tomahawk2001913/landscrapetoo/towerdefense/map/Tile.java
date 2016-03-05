package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Tile {
	private Vector2 location;
	private TextureRegion img;
	
	// Constants
	public static final float TILE_DIMENSION = 30;
	
	public Tile(Vector2 location, TextureRegion img) {
		this.location = location;
		this.img = img;
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(img, location.x, location.y, TILE_DIMENSION, TILE_DIMENSION);
	}
	
	public abstract void update(float delta);
	
	public Vector2 getLocation() {
		return location;
	}
}
