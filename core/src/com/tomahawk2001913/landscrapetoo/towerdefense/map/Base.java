package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Base implements TopTile {
	private float time;
	private Animation baseAnimation;
	private Vector2 location;
	
	public Base() {
		location = new Vector2();
	}
	
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		batch.draw(baseAnimation.getKeyFrame(time), location.x + xOffset, location.y + yOffset);
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