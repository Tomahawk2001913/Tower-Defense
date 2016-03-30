package com.tomahawk2001913.landscrapetoo.towerdefense.map.towers;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;

public class GatlingCannonTower extends Tower {
	private float damage;
	
	public GatlingCannonTower(Vector2 location, TileMap tm) {
		super(location, Tower.DEFAULT_ROTATION, 60, 0.01f, AssetHandler.gatlingCannonTower, AssetHandler.gatlingCannonTowerShooting, tm);
		
		damage = 20;
	}
	
	@Override
	public void shoot(Entity target, float delta) {
		target.damage(damage * delta);
	}
	
	@Override
	public GatlingCannonTower copy() {
		return new GatlingCannonTower(getLocation().cpy(), getTileMap());
	}
}