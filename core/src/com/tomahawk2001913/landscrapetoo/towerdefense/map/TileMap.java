package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.Playing;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.Tower;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.ButtonPanel;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.Panel;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.Text;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.TextPanel;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.UpgradeTowerButton;

public class TileMap {
	private Tiles tiles[][];
	private TopTile topTiles[][];
	private List<Entity> entities, deadEntities;
	private static List<RobotSpawner> spawners;
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
		spawners = new ArrayList<RobotSpawner>();
		
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
		spawners = new ArrayList<RobotSpawner>();
		
		for(int x = 0; x < topTiles.length; x++) {
			for(int y = 0; y < topTiles[0].length; y++) {
				if(getTopTile(x, y) instanceof Base) {
					base = (Base) getTopTile(x, y);
					base.setLocation(x * TileMap.TILE_DIMENSION, y * TileMap.TILE_DIMENSION);
					
					topTiles[x][y] = null;
				} else if(getTopTile(x, y) instanceof RobotSpawner) {
					spawners.add(((RobotSpawner) getTopTile(x, y)));
					((RobotSpawner) getTopTile(x, y)).setLocation(x * TileMap.TILE_DIMENSION, y * TileMap.TILE_DIMENSION);
					((RobotSpawner) getTopTile(x, y)).setTileMap(this);
					
					topTiles[x][y] = null;
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
					if(topTiles[x][y] instanceof Tower) topTiles[x][y].render(batch, xOffset, yOffset);
					else topTiles[x][y].render(batch, figX, figY);
				}
			}
		}
		
		for(RobotSpawner spawner : spawners) {
			spawner.render(batch, xOffset, yOffset);
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
		
		for(RobotSpawner spawner : spawners) {
			spawner.update(delta);
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
	
	public static void nextWave() {
		for(RobotSpawner spawner : spawners) {
			spawner.startWave();
		}
	}
	
	public static boolean isWaveInProgress() {
		for(RobotSpawner spawner : spawners) {
			if(spawner.getWave() != null) return true;
		}
		
		return false;
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
		int tileX = (int) ((x - xOffset) / TILE_DIMENSION), tileY = (int) ((y - yOffset) / TILE_DIMENSION);
		
		if(getTopTile(tileX, tileY) instanceof Tower) {
			Tower tower = ((Tower) getTopTile(tileX, tileY));
			
			List<Text> texts = new ArrayList<Text>();
			
			texts.add(new Text(tower.getName(), 18, 0, 0));
			texts.add(new Text("Dmg: " + tower.getDamage(), 16, 0, 0));
			texts.add(new Text("Spd: " + tower.getFireRate(), 16, 0, 0));
			texts.add(new Text("$" + tower.getPrice(), 16, 0, 0));
			
			TextPanel tp = tower.getInformation();
			ButtonPanel bp = new ButtonPanel(new Vector2(0, 0), false, new UpgradeTowerButton(0, 0, 20, 20, true, tower));
			
			Panel[] panels = {tp, bp};
			
			Playing.informationPanel.setPanels(panels);
		}
		
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
	
	public List<RobotSpawner> getSpawners() {
		return spawners;
	}
	
	public void refreshSpawnersPaths() {
		for(RobotSpawner spawner : spawners) {
			spawner.refreshPath();
		}
	}
	
	public void replaceTile(int x, int y, Tiles tile) {
		if(x < tiles.length && x > -1 && y < tiles[0].length && y > -1) {
			tiles[x][y] = tile;
			
			refreshSpawnersPaths();
		}
	}
	
	public boolean placeTopTile(int x, int y, TopTile topTile) {
		if(x < topTiles.length  && x > -1 && y < topTiles[0].length && y > -1) {
			topTiles[x][y] = topTile;
			
			refreshSpawnersPaths();
			
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