package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public class HealthBar {
	private TextureRegion color;
	
	private float originalHealth, originalWidth, width, height;
	
	// Constants.
	public static final float TYPICAL_HEIGHT = 4;
	
	public HealthBar(float originalHealth) {
		color = AssetHandler.healthBarColor;
		this.originalHealth = originalHealth;
		originalWidth = TileMap.TILE_DIMENSION;
		width = TileMap.TILE_DIMENSION;
		height = TYPICAL_HEIGHT;
	}
	
	public HealthBar(float originalHealth, float width, float height) {
		color = AssetHandler.healthBarColor;
		this.originalHealth = originalHealth;
		
		originalWidth = width;
		this.width = width;
		this.height = height;
	}
	
	public void render(SpriteBatch batch, float x, float y) {
		batch.draw(color, x, y, width, height);
	}
	
	public void setOriginalHealth(float original) {
		originalHealth = original;
	}
	
	public void setCurrentHealth(float health) {
		width = originalWidth * (health / originalHealth);
	}
}