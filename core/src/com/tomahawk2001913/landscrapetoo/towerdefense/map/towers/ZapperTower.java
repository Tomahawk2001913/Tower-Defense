package com.tomahawk2001913.landscrapetoo.towerdefense.map.towers;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.ZapperBolt;

public class ZapperTower extends Tower {
	private float bulletDamage;
	
	public ZapperTower(Vector2 location, TileMap tm) {
		super(location, Tower.DEFAULT_ROTATION, 80, 2, AssetHandler.zapperTower, AssetHandler.zapperTowerShooting, ZapperTowerUpgrades.HIGHPOWER, tm);
		bulletDamage = ZapperTowerUpgrades.ORIGINAL.getDamage();
	}
	
	@Override
	public void shoot(Entity target, float delta) {
		getTileMap().addEntity(new ZapperBolt(getLocation().cpy(), 60, getRotation(), 1, bulletDamage, getTileMap(), AssetHandler.zapperBolt));
	}
	
	@Override
	public float getDamage() {
		return bulletDamage;
	}
	
	@Override
	public int getPrice() {
		return 200;
	}
	
	@Override
	public String getName() {
		return "Zapper Tower";
	}
	
	@Override
	public Tower copy() {
		return new ZapperTower(getLocation().cpy(), getTileMap());
	}
}