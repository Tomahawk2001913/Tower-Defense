package com.tomahawk2001913.landscrapetoo.towerdefense.map.towers;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public class GatlingCannonTower extends Tower {
	public GatlingCannonTower(Vector2 location, TileMap tm) {
		super(location, Tower.DEFAULT_ROTATION, 3, 10, AssetHandler.gatlingCannonTower, AssetHandler.gatlingCannonTowerShooting, tm);
	}
}