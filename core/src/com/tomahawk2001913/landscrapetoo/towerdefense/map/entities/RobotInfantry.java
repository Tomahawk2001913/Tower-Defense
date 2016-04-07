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
	
	// Constants
	public static final int DEFAULT_WORTH = 15;
	
	public RobotInfantry(Vector2 location, TileMap tm, List<Vector2> path) {
		super(location, Entity.DEFAULT_ENTITY_DIMENSION, Entity.DEFAULT_ENTITY_DIMENSION, 20, 75, 10, DEFAULT_WORTH, true, tm, path);
		
		ria = AssetHandler.robotInfantryAnimation;
	}

	@Override
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		super.render(batch, xOffset, yOffset);
		
		Color tempColor = batch.getColor();
		batch.setColor(1, 1, 1, super.getAlpha());
		batch.draw(ria.getKeyFrame(super.getTime()), super.getLocation().x + xOffset, super.getLocation().y + yOffset, 
				getWidth(), getHeight());
		batch.setColor(tempColor);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
	}
}