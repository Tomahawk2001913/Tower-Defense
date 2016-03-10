package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.Tower;

public class TileMap {
	private Tiles tiles[][];
	private TopTile topTiles[][];
	private List<Entity> entities;
	private Base base;
	
	private float xOffset = 40, yOffset = 40;
	
	// Constants
	public static final float TILE_DIMENSION = 30;
	
	public TileMap(int width, int height) {
		tiles = new Tiles[width][height];
		topTiles = new TopTile[width][height];
		entities = new ArrayList<Entity>();
		
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
		entities = new ArrayList<Entity>();
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
		
		for(Entity entity : entities) {
			if(entity == null) continue;
			
			entity.render(batch, xOffset, yOffset);
		}
		
		//Gdx.app.log("TileMap", "" + Gdx.graphics.getFramesPerSecond());
	}
	
	public void update(float delta) {
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				if(topTiles[x][y] != null) topTiles[x][y].update(delta);
			}
		}
		
		for(Entity entity : entities) {
			if(entity == null) continue;
			
			entity.update(delta);
		}
	}
	
	public List<Vector2> findPath(Vector2 start, Vector2 finish) {
		List<Vector2> open = new ArrayList<Vector2>();
		List<Vector2> use = new ArrayList<Vector2>();
		
		Vector2 current = new Vector2(start);
		use.add(current);
		open.add(current);
		
		if(current.equals(finish)) return use;
		
		WhileLoop: while(!use.contains(finish)) {
			current = open.get(0);
			
			if(current == null) {
				open.remove(current);
				continue;
			}
			
			for(int x = 0; x < 3; x++) {
				for(int y = 0; y < 3; y++) {
					float cX = current.x - 1 + x;
					float cY = current.y - 1 + y;
					Gdx.app.log("TileMap", cX + "/" + cY);
					
					if(Math.sqrt(Math.pow(cX - finish.x, 2) + Math.pow(cY - finish.y, 2)) < Math.sqrt(Math.pow(current.x - finish.x, 2) + Math.pow(current.y - finish.y, 2))) {
						Gdx.app.log("TileMap", "first if");
						
						int cXInt = (int) cX, cYInt = (int) cY;
						
						Tiles cT = getTile(cXInt, cYInt);
						TopTile cTT = getTopTile(cXInt, cYInt);
						
						if(cT != null && (cTT == null || !cTT.isSolid()) && !cT.isSolid()) {
							Gdx.app.log("TileMap", "IF passed");
							use.add(new Vector2(cX, cY));
							open.add(new Vector2(cX, cY));
							open.remove(current);
						} else if(x == 2 && y == 2) break WhileLoop;
					}
				}
			}
		}
		Gdx.app.log("TileMap", use.toString());
		return use;
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void clearEntities() {
		entities.clear();
	}
	
	public void replaceTile(int x, int y, Tiles tile) {
		tiles[x][y] = tile;
	}
	
	public void placeTopTile(int x, int y, TopTile topTile) {
		topTiles[x][y] = topTile;
	}
	
	public Tiles getTile(int x, int y) {
		try {
			return tiles[x][y];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public TopTile getTopTile(int x, int y) {
		try {
			return topTiles[x][y];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
}