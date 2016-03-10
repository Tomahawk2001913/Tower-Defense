package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;

public class PineTree implements TopTile {
	private TextureRegion img;
	
	public PineTree() {
		img = AssetHandler.pineTree;
	}
	
	@Override
	public void render(SpriteBatch batch, float x, float y) {
		batch.draw(img, x, y, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
	}

	@Override
	public void update(float delta) {
		// Nothing needed here for now...
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}