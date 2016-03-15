package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public class RobotInfantry extends Entity {
	private Animation ria;
	
	public RobotInfantry(Vector2 location, TileMap tm, List<Vector2> path) {
		super(location, Entity.DEFAULT_ENTITY_DIMENSION, Entity.DEFAULT_ENTITY_DIMENSION, 2.5f, 75, tm, path);
		
		ria = AssetHandler.robotInfantryAnimation;
	}

	@Override
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		super.render(batch, xOffset, yOffset);
		super.setBounds(getLocation().x * TileMap.TILE_DIMENSION + xOffset, getLocation().y * TileMap.TILE_DIMENSION + xOffset, getWidth(), getHeight());
		super.setRegion(ria.getKeyFrame(getTime()));
		super.draw(batch, super.getAlpha());
		//batch.draw(ria.getKeyFrame(time), getLocation().x * TileMap.TILE_DIMENSION + xOffset, getLocation().y * TileMap.TILE_DIMENSION + xOffset, getWidth(), getHeight());
		
	}

	@Override
	public void update(float delta) {
		super.update(delta);
	}
}