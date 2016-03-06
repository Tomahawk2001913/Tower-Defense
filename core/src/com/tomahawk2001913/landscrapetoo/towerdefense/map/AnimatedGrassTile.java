package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;

public class AnimatedGrassTile implements TopTile {
	private Animation grassAnimation;
	private float time;
	
	public AnimatedGrassTile() {
		this.grassAnimation = AssetHandler.animatedGrass;
		time = 0;
	}
	
	@Override
	public void render(SpriteBatch batch, float x, float y) {
		batch.draw(grassAnimation.getKeyFrame(time), x, y, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
	}

	@Override
	public void update(float delta) {
		time += delta;
	}
}