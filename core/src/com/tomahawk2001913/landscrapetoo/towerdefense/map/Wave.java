package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.RobotInfantry;

public class Wave {
	private RobotSpawner rs;
	
	private int number;
	
	public Wave(int number, RobotSpawner rs) {
		this.number = number;
		
		this.rs = rs;
	}
	
	public Entity nextSpawn(Vector2 location, TileMap tm) {
		return new RobotInfantry(location, tm, tm.findPath(location, tm.getBase().getLocation()));
	}
	
	public int getNumber() {
		return number;
	}
}