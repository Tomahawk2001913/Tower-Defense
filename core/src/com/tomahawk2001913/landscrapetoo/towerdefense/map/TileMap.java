package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import java.util.ArrayList;
import java.util.Collections;
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
	
	private boolean touched = false, gameOver = false;
	
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
					
					topTiles[x][y] = null;
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
		// Render most of the stuff.
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				float figX = x * TILE_DIMENSION + xOffset, figY = y * TILE_DIMENSION + yOffset;
				batch.draw(tiles[x][y].getTextureRegion(), figX, figY, TILE_DIMENSION, TILE_DIMENSION);
				if(topTiles[x][y] != null) {
					if(topTiles[x][y] instanceof Tower || topTiles[x][y] instanceof RobotSpawner) topTiles[x][y].render(batch, xOffset, yOffset);
					else topTiles[x][y].render(batch, figX, figY);
				}
			}
		}
		
		// Render the base
		base.render(batch, xOffset, yOffset);
		
		// Render the previously ignored entities.
		for(Entity entity : entities) {
			if(entity == null) continue;
			
			entity.render(batch, xOffset, yOffset);
		}
	}
	
	public void update(float delta) {
		for(Entity entity : deadEntities) {
			entities.remove(entity);
		}
		deadEntities.clear();
		
		if(gameOver) return;
		
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				if(topTiles[x][y] != null) topTiles[x][y].update(delta);
			}
		}
		
		base.update(delta);
		
		for(Entity entity : entities) {
			if(entity == null) continue;
			
			entity.update(delta);
		}
		
		if(base.getHealth() <= 0) {
			gameOver = true;
		}
	}
	
	public List<Vector2> oldPathFinding(Vector2 start, Vector2 finish) {
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
					
					if(cT != null && (cTT == null || !cTT.isSolid()) && !cT.isSolid()) {
						if(!closed.contains(currentCoords) && (closest == null || (!closest.equals(current) &&
								!use.contains(currentCoords) && getDistance(currentCoords, finish) < getDistance(closest, finish)))) {
							closest = currentCoords;
						}
						
						if(x == 2 && y == 2) {
							break ForLoops;
						}
					} else if(x == 2 && y == 2) {
						if(current.equals(closest) || closest == null)
							break WhileLoop;
					}
				}
			}
			
			// Attempt to prevent an infinite loop.
			if(use.contains(closest)) {
				if(use.size() > 1) {
					closed.add(use.get(use.size() - 1));
					use.remove(use.get(use.size() - 1));
					
					if(use.size() > 2) {
						closed.add(closest);
						use.remove(use.indexOf(closest));
					}
				}
			} else use.add(closest);
		}
		
		for(Vector2 loc : use) {
			loc.scl(TileMap.TILE_DIMENSION);
		}
		
		return use;
	}
	
	public List<Vector2> findPath(Vector2 start, Vector2 end) {
		List<Node> open = new ArrayList<Node>();
		List<Node> closed = new ArrayList<Node>();
		
		Node findPath = null;
		
		open.add(new Node(start, null, end));
		
		while(!open.isEmpty()) {
			Node current = null;
			
			for(Node check : open) {
				if(current == null || check.getFCost() < current.getFCost()) current = check;
			}
			
			open.remove(current);
			if(listContainsNode(closed, current)) System.out.println("Uh oh.");
			closed.add(current);
			
			if(current.getLocation().equals(end)) {
				System.out.println("found end");
				findPath = current;
				break;
			}
			
			for(int x = -1; x < 2; x++) {
				for(int y = -1; y < 2; y++) {
					if(x == 0 && y == 0) continue;
					
					Node check = new Node(current.getLocation().cpy().add(x, y), current, end);
					
					if(!isTraversable((int) (check.getLocation().x), (int) (check.getLocation().y)) || listContainsNode(closed, check)) 
						continue;
					
					if(!listContainsNode(open, check)) {
						open.add(check);
					} else {
						Node tempNode = findNode(open, check);
						
						if(check.getGCost() < tempNode.getGCost()) {
							open.remove(tempNode);
							open.add(check);
						}
					}
				}
			}
			
			// Avoid NullPointerException?
			findPath = current;
		}
		
		List<Vector2> path = new ArrayList<Vector2>();
		
		while(findPath.getParent() != null) {
			path.add(findPath.getLocation().scl(TILE_DIMENSION));
			findPath = findPath.getParent();
		}
		
		Collections.reverse(path);
		
		return path;
	}
	
	public boolean listContainsNode(List<Node> list, Node check) {
		for(Node node : list) {
			if(node.getLocation().equals(check.getLocation())) return true;
		}
		
		return false;
	}
	
	public Node findNode(List<Node> list, Node find) {
		for(Node node : list) {
			if(node.getLocation().equals(find.getLocation())) return node;
		}
		
		return null;
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

			bounds.set(xOffset, yOffset, tiles.length * TileMap.TILE_DIMENSION, tiles[0].length * TileMap.TILE_DIMENSION);
			
			return true;
		}
		
		return false;
	}
	
	public static double getDistance(Vector2 start, Vector2 finish) {
		return Math.sqrt(Math.pow(start.x - finish.x, 2) + Math.pow(start.y - finish.y, 2));
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public void addEntity(Entity entity) {
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
	
	public boolean placeTopTile(int x, int y, TopTile topTile) {
		if(x < topTiles.length  && x > -1 && y < topTiles[0].length && y > -1) {
			topTiles[x][y] = topTile;
			return true;
		}
		
		return false;
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
	
	public boolean isTraversable(int x, int y) {
		return getTile(x, y) != null && !getTile(x, y).isSolid() && (getTopTile(x, y) == null || !getTopTile(x, y).isSolid());
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