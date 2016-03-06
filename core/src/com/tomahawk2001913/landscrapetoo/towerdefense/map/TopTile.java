package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface TopTile {
	public abstract void render(SpriteBatch batch, float x, float y);
	public abstract void update(float delta);
}
