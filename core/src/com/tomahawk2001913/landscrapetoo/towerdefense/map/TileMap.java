package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TileMap {
	Tile tiles[][];
	
	public TileMap(int width, int height) {
		tiles = new Tile[width][height];
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				tiles[x][y] = new GrassTile(false);
			}
		}
	}
	
	public TileMap(Tile tiles[][]) {
		this.tiles = tiles;
	}
	
	public void render(SpriteBatch batch) {
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				tiles[x][y].render(batch, x * Tile.TILE_DIMENSION, y * Tile.TILE_DIMENSION);
			}
		}
		
		Gdx.app.log("TileMap", "" + Gdx.graphics.getFramesPerSecond());
	}
	
	public void update(float delta) {
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				tiles[x][y].update(delta);;
			}
		}
	}
}