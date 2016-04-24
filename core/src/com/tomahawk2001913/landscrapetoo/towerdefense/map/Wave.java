package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.RobotDrill;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.RobotInfantry;
import com.tomahawk2001913.landscrapetoo.towerdefense.screens.GameScreen;

public class Wave {
	private RobotSpawner rs;
	
	private int number, entitiesLeft;
	private float spawnTime = 0, spawnInterval;
	
	// Constants
	public static final int ORIGINAL_WAVE_SIZE = 10;
	public static final float WAVE_MULTIPLIER = 1.5f, DEFAULT_SPAWN_INTERVAL = 3.0f;
	
	public Wave(int number, RobotSpawner rs) {
		this.number = number;
		
		this.rs = rs;
		entitiesLeft = (int) (number  * WAVE_MULTIPLIER * ORIGINAL_WAVE_SIZE);
		spawnInterval = DEFAULT_SPAWN_INTERVAL - number / DEFAULT_SPAWN_INTERVAL + 0.1f;
	}
	
	public Entity nextSpawn(Vector2 location, List<Vector2> path, TileMap tm) {
		if(GameScreen.getRandom().nextBoolean() && number > 3) return new RobotDrill(location, tm, path);
		else return new RobotInfantry(location, tm, path);
	}
	
	public void update(float delta) {
		spawnTime += delta;
		
		if(spawnTime >= spawnInterval) {
			spawnTime -= spawnInterval;
			rs.spawnEntity(nextSpawn(rs.getLocation().cpy(), rs.getPath(), rs.getTileMap()));
			entitiesLeft--;
		}
		
		if(entitiesLeft <= 0) {
			rs.endWave();
		}
	}
	
	public int getNumber() {
		return number;
	}
}