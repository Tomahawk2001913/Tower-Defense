package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;

public class GrassTile extends Tile {
	private boolean useAnimatedGrass;
	
	private Animation animatedGrass;
	private float time;
	
	public GrassTile(boolean useAnimatedGrass) {
		super(AssetHandler.grassTile);
		this.useAnimatedGrass = useAnimatedGrass;
		
		if(useAnimatedGrass) animatedGrass = AssetHandler.animatedGrass;
	}
	
	@Override
	public void render(SpriteBatch batch, float x, float y) {
		super.render(batch, x, y);
		if(useAnimatedGrass) batch.draw(animatedGrass.getKeyFrame(time), x, y, Tile.TILE_DIMENSION, Tile.TILE_DIMENSION);
	}
	
	public void update(float delta) {
		if(useAnimatedGrass) time += delta;
	}
}
