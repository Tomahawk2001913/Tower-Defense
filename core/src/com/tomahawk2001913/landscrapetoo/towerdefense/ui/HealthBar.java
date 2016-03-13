package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;

public class HealthBar {
	private Entity entity;
	private float originalHealth;
	
	public HealthBar(Entity entity) {
		this.entity = entity;
		originalHealth = entity.getHealth();
	}
	
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		// Could be optimized by only figuring health percent after damaged.
		batch.draw(AssetHandler.barrierTile, entity.getLocation().x * TileMap.TILE_DIMENSION + xOffset, entity.getLocation().y * TileMap.TILE_DIMENSION + yOffset + entity.getHeight(), entity.getWidth() * entity.getHealth() / originalHealth, 4);
	}
}