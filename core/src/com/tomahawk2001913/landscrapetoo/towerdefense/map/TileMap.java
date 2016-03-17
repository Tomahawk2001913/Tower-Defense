package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.Tower;

public class TileMap {
	private Tiles tiles[][];
	private TopTile topTiles[][];
	private List<Entity> entities, deadEntities;
	private Base base;
	
	private Rectangle bounds;
	
	private float xOffset = 0, yOffset = 0, xTouchOffset = 0, yTouchOffset = 0;
	
	private boolean touched = false;
	
	// Constants
	public static final float TILE_DIMENSION = 30;
	
	public TileMap(int width, int height) {
		tiles = new Tiles[width][height];
		topTiles = new TopTile[width][height];
		entities = new ArrayList<Entity>();
		deadEntities = new ArrayList<Entity>();
		
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				tiles[x][y] = Tiles.GRASS;
				topTiles[x][y] = null;
			}
		}
		
		bounds = new Rectangle(xOffset, yOffset, tiles.length * TileMap.TILE_DIMENSION, tiles[0].length * TileMap.TILE_DIMENSION);
	}
	
	public TileMap(Tiles tiles[][], TopTile topTiles[][]) {
		this.tiles = tiles;
		this.topTiles = topTiles;
		entities = new ArrayList<Entity>();
		deadEntities = new ArrayList<Entity>();
		
		for(int x = 0; x < topTiles.length; x++) {
			for(int y = 0; y < topTiles[0].length; y++) {
				if(getTopTile(x, y) instanceof Base) {
					base = (Base) getTopTile(x, y);
					base.setLocation(x * TileMap.TILE_DIMENSION, y * TileMap.TILE_DIMENSION);
				} else if(getTopTile(x, y) instanceof RobotSpawner) {
					((RobotSpawner) getTopTile(x, y)).setLocation(x * TileMap.TILE_DIMENSION, y * TileMap.TILE_DIMENSION);
					((RobotSpawner) getTopTile(x, y)).setTileMap(this);
				}
			}
		}
		
		if(base == null) Gdx.app.log("TileMap", "No predefined base detected.");
		
		bounds = new Rectangle(xOffset, yOffset, tiles.length * TileMap.TILE_DIMENSION, tiles[0].length * TileMap.TILE_DIMENSION);
	}
	
	public void render(SpriteBatch batch) {
		// Render everything but entities.
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				float figX = x * TILE_DIMENSION + xOffset, figY = y * TILE_DIMENSION + yOffset;
				batch.draw(tiles[x][y].getTextureRegion(), figX, figY, TILE_DIMENSION, TILE_DIMENSION);
				if(topTiles[x][y] != null) {
					if(topTiles[x][y] instanceof Tower || topTiles[x][y] instanceof Base || topTiles[x][y] instanceof RobotSpawner) topTiles[x][y].render(batch, xOffset, yOffset);
					else topTiles[x][y].render(batch, figX, figY);
				}
			}
		}
		
		// Render the previously ignored entities.
		for(Entity entity : entities) {
			if(entity == null) continue;
			
			entity.render(batch, xOffset, yOffset);
		}
	}
	
	public void update(float delta) {
		bounds.set(xOffset, yOffset, tiles.length * TileMap.TILE_DIMENSION, tiles[0].length * TileMap.TILE_DIMENSION);
		
		for(Entity entity : deadEntities) {
			entities.remove(entity);
		}
		deadEntities.clear();
		
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
		List<Vector2> use = new ArrayList<Vector2>();
		List<Vector2> closed = new ArrayList<Vector2>();
		
		Vector2 current = new Vector2(start);
		use.add(current);
		
		if(current.equals(finish)) return use;
		
		WhileLoop: while(!use.contains(finish)) {
			current = use.get(use.size() - 1);
			
			if(current == null) {
				continue;
			}
			
			Vector2 closest = null;
			
			ForLoops:
			for(int x = 0; x < 3; x++) {
				for(int y = 0; y < 3; y++) {
					if(x == 1 && y == 1) continue;
					
					float cX = current.x - 1 + x;
					float cY = current.y - 1 + y;
					
					Vector2 currentCoords = new Vector2(cX, cY);
					
					// This conversion to int means it is much better to give integer arguments.
					int cXInt = (int) cX, cYInt = (int) cY;
					
					Tiles cT = getTile(cXInt, cYInt);
					TopTile cTT = getTopTile(cXInt, cYInt);
					System.out.println(current);
					if(cT != null && (cTT == null || !cTT.isSolid()) && !cT.isSolid()) {
						if(closest == null || (!closest.equals(current) && !closed.contains(currentCoords) &&
								!use.contains(currentCoords) && getDistance(currentCoords, finish) < getDistance(closest, finish))) {
							closest = currentCoords;
						}
						
						if(x == 2 && y == 2) {
							break ForLoops;
						}
					} else if(x == 2 && y == 2 && closest.equals(current)) {
						if(getDistance(finish, current) < 2) return use;
						break WhileLoop;
					}
				}
			}
			
			use.add(closest);
		}
		
		List<Vector2> newUse = new ArrayList<Vector2>();
		
		for(Vector2 loc : use) {
			newUse.add(loc.scl(TileMap.TILE_DIMENSION));
		}
		
		return newUse;
	}
	
	public boolean touchDown(float x, float y) {
		if(bounds.contains(x, y)) {
			touched = true;
			
			xTouchOffset = xOffset - x;
			yTouchOffset = yOffset - y;
			
			return true;
		}
		
		return false;
	}
	
	public boolean touchUp(float x, float y) {
		xTouchOffset = 0;
		yTouchOffset = 0;
		if(touched) {
			touched = false;
			return true;
		}
		return false;
	}
	
	public boolean touchDragged(float x, float y) {
		if(touched) {
			xOffset = x + xTouchOffset;
			yOffset = y + yTouchOffset;
			return true;
		}
		
		return false;
	}
	
	public double getDistance(Vector2 start, Vector2 finish) {
		return Math.sqrt(Math.pow(start.x - finish.x, 2) + Math.pow(start.y - finish.y, 2));
	}
	
	public void addEntity(Entity entity) {
		System.out.println("ADDED");
		
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		deadEntities.add(entity);
	}
	
	public void clearEntities() {
		entities.clear();
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public void replaceTile(int x, int y, Tiles tile) {
		if(x < tiles.length && x > -1 && y < tiles[0].length && y > -1)
			tiles[x][y] = tile;
	}
	
	public void placeTopTile(int x, int y, TopTile topTile) {
		if(x < topTiles.length  && x > -1 && y < topTiles[0].length && y > -1)
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
	
	public Base getBase() {
		return base;
	}
	
	public float getXOffset() {
		return xOffset;
	}
	
	public float getYOffset() {
		return yOffset;
	}
}