package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;

public class HealthBar {
	private TextureRegion color;
	
	private float originalHealth, width;
	
	// Constants.
	public static final float HEIGHT = 4;
	
	public HealthBar(float originalHealth) {
		color = AssetHandler.healthBarColor;
		this.originalHealth = originalHealth;
		width = TileMap.TILE_DIMENSION;
	}
	
	public void render(SpriteBatch batch, float x, float y) {
		batch.draw(color, x, y, width, HEIGHT);
	}
	
	public void setOriginalHealth(float original) {
		originalHealth = original;
	}
	
	public void setCurrentHealth(float health) {
		width = TileMap.TILE_DIMENSION * (health / originalHealth);
	}
}