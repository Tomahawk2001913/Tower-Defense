package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public class ZapperBolt extends Bullet {
	private int robots;
	
	public ZapperBolt(Vector2 location, float speed, float rotation, float health, float damage, int robots, TileMap tm, TextureRegion img) {
		super(location, speed, rotation, health, damage, tm, img);
		this.robots = robots;
	}

	@Override
	public void hit(Entity entity) {
		int zapped = 0;
		for(Entity check : getTileMap().getEntities()) {
			if(zapped >= robots) return;
			
			if(TileMap.getDistance(check.getLocation(), entity.getLocation()) < TileMap.TILE_DIMENSION * 2) {
				check.damage(getDamage());
				zapped++;
			}
		}
	}
}