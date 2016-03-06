package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Tile {
	private TextureRegion img;
	
	// Constants
	public static final float TILE_DIMENSION = 30;
	
	public Tile(TextureRegion img) {
		this.img = img;
	}
	
	public void render(SpriteBatch batch, float x, float y) {
		batch.draw(img, x, y, TILE_DIMENSION, TILE_DIMENSION);
	}
	
	public abstract void update(float delta);
}
