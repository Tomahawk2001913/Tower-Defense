package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public class LaserBeam extends Bullet {

	public LaserBeam(Vector2 location, float speed, float rotation, float health, float damage, TileMap tm) {
		super(location, speed, rotation, health, damage, tm, AssetHandler.laserBeam);
	}

	@Override
	public void hit(Entity entity) {
		entity.damage(getDamage());
	}
}