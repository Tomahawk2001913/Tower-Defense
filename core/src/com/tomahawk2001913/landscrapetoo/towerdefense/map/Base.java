package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.HealthBar;

public class Base implements TopTile {
	private float time;
	private Animation baseAnimation;
	private Vector2 location;
	private HealthBar hb;
	private Rectangle bounds;
	
	private float health;
	
	public Base() {
		location = new Vector2();
		baseAnimation = AssetHandler.factoryAnimation;
		health = 200;
		
		hb = new HealthBar(health, TileMap.TILE_DIMENSION * 2, HealthBar.TYPICAL_HEIGHT);
		
		bounds = new Rectangle(location.x, location.y, TileMap.TILE_DIMENSION * 2, TileMap.TILE_DIMENSION * 2);
	}
	
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		batch.draw(baseAnimation.getKeyFrame(time), location.x + xOffset, location.y + yOffset, bounds.width, bounds.height);
		hb.render(batch, location.x + xOffset, location.y + yOffset + bounds.getHeight());
	}
	
	public void update(float delta) {
		time += delta;
		
		if(health <= 0) {
			health = 0;
		}
	}
	
	public void damage(float damage) {
		health -= damage;
		hb.setCurrentHealth(health);
	}
	
	public void setLocation(float x, float y) {
		location.set(x, y);
		bounds.set(location.x, location.y, TileMap.TILE_DIMENSION * 2, TileMap.TILE_DIMENSION * 2);
	}
	
	public Vector2 getLocation() {
		return location;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public float getHealth() {
		return health;
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}