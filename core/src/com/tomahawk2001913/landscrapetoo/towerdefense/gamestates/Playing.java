package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.RobotInfantry;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.GatlingCannonTower;

public class Playing extends GameState {
	private TileMap tm;
	
	public Playing() {
		
		//tm = new TileMap(3, 3);
	}
	
	@Override
	public void changeTo(GameStateManager superManager) {
		super.changeTo(superManager);
		tm = AssetHandler.loadMap("Maps/GrassyArea.tdm");
		tm.placeTopTile(0, 0, new GatlingCannonTower(new Vector2(0, 0), tm));
		
		tm.addEntity(new RobotInfantry(new Vector2(5, 2), tm, tm.findPath(new Vector2(5, 2), new Vector2(1, 1))));
	}
	
	@Override
	public void changeFrom() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		tm.render(batch);
	}

	@Override
	public void update(float delta) {
		tm.update(delta);
	}
	
	@Override
	public void resize(float width, float height) {
		
	}
	
	@Override
	public boolean touchDown(float x, float y) {
		return false;
	}
	
	@Override
	public boolean touchUp(float x, float y) {
		return false;
	}
	
	@Override
	public boolean touchDragged(float x, float y) {
		return false;
	}
}