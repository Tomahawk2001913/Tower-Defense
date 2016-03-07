package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;

public enum Tiles {
	GRASS(AssetHandler.grassTile, false), 
	STONE(AssetHandler.stoneTile, false), 
	SAND(AssetHandler.sandTile, false), 
	WATER(AssetHandler.waterTile, true), 
	DIRT(AssetHandler.dirtTile, false),
	SNOW(AssetHandler.snowTile, false),
	BARRIER(AssetHandler.barrierTile, true);
	
	private boolean solid;
	
	private TextureRegion img;
	
	private Tiles(TextureRegion img, boolean solid) {
		this.solid = solid;
		this.img = img;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public TextureRegion getTextureRegion() {
		return img;
	}
}
