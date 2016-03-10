package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface TopTile {
	public void render(SpriteBatch batch, float x, float y);
	public void update(float delta);
	public boolean isSolid();
}
