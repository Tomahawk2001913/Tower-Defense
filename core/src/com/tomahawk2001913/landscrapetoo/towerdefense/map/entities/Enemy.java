package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.Base;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public abstract class Enemy extends Entity {

	public Enemy(Vector2 location, float width, float height, float speed, float health, float damage, int worth, TileMap tm, List<Vector2> path) {
		super(location, width, height, speed, health, damage, worth, tm, path);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		if(getBounds().overlaps(getTileMap().getBase().getBounds())) {
			attackBase(getTileMap().getBase(), getDamage(), delta);
		}
	}
	
	public abstract void attackBase(Base base, float damage, float delta);
}