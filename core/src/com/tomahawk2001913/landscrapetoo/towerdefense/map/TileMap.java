package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.Tower;

public class TileMap {
	private Tiles tiles[][];
	private TopTile topTiles[][];
	
	private float xOffset = 40, yOffset = 40;
	
	// Constants
	public static final float TILE_DIMENSION = 30;
	
	public TileMap(int width, int height) {
		tiles = new Tiles[width][height];
		topTiles = new TopTile[width][height];
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				tiles[x][y] = Tiles.GRASS;
				topTiles[x][y] = null;
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
				float figX = x * TILE_DIMENSION + xOffset, figY = y * TILE_DIMENSION + yOffset;
				batch.draw(tiles[x][y].getTextureRegion(), figX, figY, TILE_DIMENSION, TILE_DIMENSION);
				if(topTiles[x][y] != null) {
					if(topTiles[x][y] instanceof Tower) topTiles[x][y].render(batch, xOffset, yOffset);
					else topTiles[x][y].render(batch, figX, figY);
				}
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
	
	public List<Vector2> findPath(Vector2 start, Vector2 finish) {
		List<Vector2> open = new ArrayList<Vector2>();
		List<Vector2> use = new ArrayList<Vector2>();
		
		Vector2 current = new Vector2(start);
		use.add(current);
		
		if(current.equals(finish)) return use;
		
		while(open.size() > 0) {
			current = open.get(0);
			
			if(current == null) {
				open.remove(current);
				continue;
			}
			
			for(int x = 0; x < 3; x++) {
				for(int y = 0; y < 3; y++) {
					float cX = current.x - 3 + x;
					float cY = current.y - 3 + y;
					
					if(Math.abs(cX - finish.x) > Math.abs(current.x - finish.x) && Math.abs(cY - finish.y) < Math.abs(cY - finish.y)) {
						if(!getTile((int) cX, (int) cY).isSolid() && getTopTile((int) cX, (int) cY) == null) {
							use.add(new Vector2(cX, cY));
						}
					}
				}
			}
		}
		
		return use;
	}
	
	public void replaceTile(int x, int y, Tiles tile) {
		tiles[x][y] = tile;
	}
	
	public void placeTopTile(int x, int y, TopTile topTile) {
		topTiles[x][y] = topTile;
	}
	
	public Tiles getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public TopTile getTopTile(int x, int y) {
		return topTiles[x][y];
	}
}