package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.RobotInfantry;

public class Wave {
	private RobotSpawner rs;
	
	private int number, entityNumber;
	
	public Wave(int number, RobotSpawner rs) {
		this.number = number;
		
		this.rs = rs;
		entityNumber = 0;
	}
	
	public Entity nextSpawn(Vector2 location, TileMap tm) {
		if(number < Waves.values().length) {
			
		}
		return new RobotInfantry(location, tm, tm.findPath(new Vector2(location.x / TileMap.TILE_DIMENSION, location.y / TileMap.TILE_DIMENSION), new Vector2((int) (tm.getBase().getLocation().x / TileMap.TILE_DIMENSION), (int) (tm.getBase().getLocation().y / TileMap.TILE_DIMENSION))));
	}
	
	public int getNumber() {
		return number;
	}
}