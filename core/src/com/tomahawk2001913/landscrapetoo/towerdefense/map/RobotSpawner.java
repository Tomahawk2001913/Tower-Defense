package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;

public class RobotSpawner implements TopTile {
	private TextureRegion img;
	private Vector2 location;
	private TileMap tm;
	
	private Wave currentWave;
	
	private float timeBetweenSpawns, spawnTimeCounter;
	
	public RobotSpawner(Vector2 location, float timeBetweenSpawns) {
		this.img = AssetHandler.robotSpawn;
		this.location = location;
		
		currentWave = new Wave(0, this);
		
		this.timeBetweenSpawns = timeBetweenSpawns;
		spawnTimeCounter = 0;
	}
	
	@Override
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		batch.draw(img, location.x + xOffset, location.y + yOffset, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
	}

	@Override
	public void update(float delta) {
		spawnTimeCounter += delta;
		
		if(spawnTimeCounter >= timeBetweenSpawns) {
			spawnTimeCounter -= timeBetweenSpawns;
			if(currentWave != null) currentWave.nextSpawn(location, tm);
			
		}
	}
	
	public void setTileMap(TileMap tm) {
		this.tm = tm;
	}
	
	public void startWave() {
		currentWave = new Wave(currentWave.getNumber() + 1, this);
	}
	
	public void setLocation(float x, float y) {
		location.x = x;
		location.y = y;
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}
}