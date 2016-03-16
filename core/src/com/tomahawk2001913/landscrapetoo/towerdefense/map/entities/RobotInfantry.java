package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
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
		
		Color tempColor = batch.getColor();
		batch.setColor(1, 1, 1, super.getAlpha());
		batch.draw(ria.getKeyFrame(super.getTime()), super.getLocation().x * TileMap.TILE_DIMENSION + xOffset, super.getLocation().y * TileMap.TILE_DIMENSION + yOffset, 
				TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
		batch.setColor(tempColor);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
	}
}