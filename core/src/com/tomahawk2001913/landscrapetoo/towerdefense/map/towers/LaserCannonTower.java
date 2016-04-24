package com.tomahawk2001913.landscrapetoo.towerdefense.map.towers;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.LaserBeam;

public class LaserCannonTower extends Tower {

	public LaserCannonTower(Vector2 location, TileMap tm) {
		super(location, Tower.DEFAULT_ROTATION, 90, 1, AssetHandler.laserCannonTower, AssetHandler.laserCannonTowerShooting, LaserCannonUpgrades.ORIGINAL, tm);
		setFireRate(LaserCannonUpgrades.ORIGINAL.getFireRate());
	}

	@Override
	public void shoot(Entity target, float delta) {
		getTileMap().addEntity(new LaserBeam(getLocation().cpy(), 85, getRotation(), 1, LaserCannonUpgrades.ORIGINAL.getDamage(), getTileMap()));
	}

	@Override
	public void upgrade() {
		// Nothing for now...
	}

	@Override
	public float getDamage() {
		return LaserCannonUpgrades.ORIGINAL.getDamage();
	}

	@Override
	public int getPrice() {
		return LaserCannonUpgrades.ORIGINAL.getPrice();
	}

	@Override
	public String getName() {
		return "Laser Tower";
	}

	@Override
	public Tower copy() {
		return new LaserCannonTower(new Vector2(0, 0), getTileMap());
	}

}
