package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState {
	private GameStateManager superManager;
	
	public void changeTo(GameStateManager superManager) {
		this.superManager = superManager;
	}
	
	public GameStateManager getCurrentGameStateManager() {
		return superManager;
	}
	
	public abstract void changeFrom();
	
	public abstract void render(SpriteBatch batch);
	
	public abstract void update(float delta);
	
	public abstract void resize(float width, float height);
	
	public abstract boolean touchDown(float x, float y);
	
	public abstract boolean touchUp(float x, float y);
	
	public abstract boolean touchDragged(float x, float y);
}