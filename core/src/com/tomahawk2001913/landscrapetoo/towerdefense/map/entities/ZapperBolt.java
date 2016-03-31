package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public class ZapperBolt extends Bullet {
	public ZapperBolt(Vector2 location, float speed, float rotation, float health, float damage, boolean hostile,
			TileMap tm, TextureRegion img) {
		super(location, speed, rotation, health, damage, hostile, tm, img);
	}

	@Override
	public void hit(Entity entity) {
		for(Entity check : getTileMap().getEntities()) {
			if(getTileMap().getDistance(check.getLocation(), entity.getLocation()) < 75) check.damage(getDamage());
		}
	}
}