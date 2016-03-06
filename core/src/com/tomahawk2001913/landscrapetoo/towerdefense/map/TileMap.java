package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileMap {
	Tiles tiles[][];
	TopTile topTiles[][];
	
	// Constants
	public static final float TILE_DIMENSION = 30;
	
	public TileMap(int width, int height) {
		tiles = new Tiles[width][height];
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				tiles[x][y] = Tiles.GRASS;
			}
		}
	}
	
	public TileMap(Tiles tiles[][], TopTile topTiles[][]) {
		this.tiles = tiles;
		this.topTiles = topTiles;
	}
	
	public void render(SpriteBatch batch) {
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				//tiles[x][y].render(batch, x * Tile.TILE_DIMENSION, y * Tile.TILE_DIMENSION);
				batch.draw(tiles[x][y].getTextureRegion(), x * TILE_DIMENSION, y * TILE_DIMENSION, TILE_DIMENSION, TILE_DIMENSION);
				if(topTiles[x][y] != null) topTiles[x][y].render(batch, x * TILE_DIMENSION, y * TILE_DIMENSION);
			}
		}
		
		Gdx.app.log("TileMap", "" + Gdx.graphics.getFramesPerSecond());
	}
	
	public void update(float delta) {
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				if(topTiles[x][y] != null) topTiles[x][y].update(delta);
			}
		}
	}
}