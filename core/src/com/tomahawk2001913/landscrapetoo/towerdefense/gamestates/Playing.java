package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public class Playing extends GameState {
	private TileMap tm;
	
	public Playing() {
		tm = new TileMap(16, 16);
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