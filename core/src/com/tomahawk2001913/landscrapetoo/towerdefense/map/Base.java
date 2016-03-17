package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;

public class Base implements TopTile {
	private float time;
	private Animation baseAnimation;
	private Vector2 location;
	
	public Base() {
		location = new Vector2();
		baseAnimation = AssetHandler.gatlingCannonTowerShootingAnimation;
	}
	
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		batch.draw(baseAnimation.getKeyFrame(time), location.x + xOffset, location.y + yOffset, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
	}
	
	public void update(float delta) {
		time += delta;
	}
	
	public void setLocation(float x, float y) {
		location.set(x, y);
	}
	
	public Vector2 getLocation() {
		return location;
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}