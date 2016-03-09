package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public class RobotInfantry extends Entity {
	private Animation ria;
	
	private float time;
	
	public RobotInfantry(Vector2 location, TileMap tm) {
		super(location, Entity.DEFAULT_ENTITY_DIMENSION, Entity.DEFAULT_ENTITY_DIMENSION, tm);
		
		ria = AssetHandler.robotInfantryAnimation;
		
		time = 0;
	}

	@Override
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		batch.draw(ria.getKeyFrame(time), getLocation().x + xOffset, getLocation().y + xOffset, getWidth(), getHeight());
	}

	@Override
	public void update(float delta) {
		time += delta;
	}
}