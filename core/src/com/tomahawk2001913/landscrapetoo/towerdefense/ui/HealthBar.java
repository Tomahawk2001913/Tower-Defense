package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;

public class HealthBar {
	private TextureRegion color;
	
	private Entity entity;
	private float originalHealth;
	
	public HealthBar(Entity entity) {
		color = AssetHandler.healthBarColor;
		this.entity = entity;
		originalHealth = entity.getHealth();
	}
	
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		// Could be optimized by only figuring health percent after damaged.
		batch.draw(color, entity.getLocation().x + xOffset, entity.getLocation().y + yOffset + entity.getHeight(), entity.getWidth() * entity.getHealth() / originalHealth, 4);
	}
}