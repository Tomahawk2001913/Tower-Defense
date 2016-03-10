package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Base {
	private float time;
	private Animation baseAnimation;
	private Vector2 location;
	
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		batch.draw(baseAnimation.getKeyFrame(time), location.x + xOffset, location.y + yOffset);
	}
	
	public void update(float delta) {
		time += delta;
	}
}