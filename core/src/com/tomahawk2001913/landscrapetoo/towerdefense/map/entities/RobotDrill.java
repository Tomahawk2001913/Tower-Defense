package com.tomahawk2001913.landscrapetoo.towerdefense.map.entities;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.Base;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public class RobotDrill extends Enemy {
	private TextureRegion img;
	
	// Constants
	public static final int DEFAULT_WORTH = 15;
		
	public RobotDrill(Vector2 location, TileMap tm, List<Vector2> path) {
		super(location, Entity.DEFAULT_ENTITY_DIMENSION, Entity.DEFAULT_ENTITY_DIMENSION, 25, 100, 15, DEFAULT_WORTH, tm, path);
		
		img = AssetHandler.drillBot;
	}
	
	@Override
	public void render(SpriteBatch batch, float xOffset, float yOffset) {
		super.render(batch, xOffset, yOffset);
		
		batch.draw(img, getLocation().x + xOffset, getLocation().y + yOffset, getWidth(), getHeight());
		
	}

	@Override
	public void attackBase(Base base, float damage, float delta) {
		base.damage(damage * delta);
	}
}