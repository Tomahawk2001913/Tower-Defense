package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;

public class RobotSpawner implements TopTile {
	private TextureRegion img;
	private Vector2 location;
	private TileMap tm;
	
	private List<Vector2> path;
	
	private Wave currentWave;
	
	private int waveNumber = 0;
	
	public RobotSpawner(Vector2 location) {
		this.img = AssetHandler.robotSpawn;
		this.location = location;
		
		startWave();
	}
	
	@Override
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		batch.draw(img, location.x + xOffset, location.y + yOffset, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
	}

	@Override
	public void update(float delta) {
		if(currentWave != null) currentWave.update(delta);
	}
	
	public void setTileMap(TileMap tm) {
		this.tm = tm;
		
		path = tm.findPath(new Vector2(location.x / TileMap.TILE_DIMENSION, location.y / TileMap.TILE_DIMENSION), new Vector2(tm.getBase().getLocation().x / TileMap.TILE_DIMENSION, tm.getBase().getLocation().y / TileMap.TILE_DIMENSION));
	}
	
	public void startWave() {
		currentWave = new Wave(waveNumber + 1, this);
		waveNumber++;
	}
	
	public void endWave() {
		currentWave = null;
	}
	
	public Wave getWave() {
		return currentWave;
	}
	
	public void refreshPath() {
		path = tm.findPath(new Vector2(location.x / TileMap.TILE_DIMENSION, location.y / TileMap.TILE_DIMENSION), new Vector2(tm.getBase().getLocation().x / TileMap.TILE_DIMENSION, tm.getBase().getLocation().y / TileMap.TILE_DIMENSION));
	}
	
	public void spawnEntity(Entity entity) {
		tm.addEntity(entity);
	}
	
	public void setLocation(float x, float y) {
		location.x = x;
		location.y = y;
	}
	
	protected Vector2 getLocation() {
		return location;
	}
	
	protected List<Vector2> getPath() {
		return path;
	}
	
	protected TileMap getTileMap() {
		return tm;
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}
}