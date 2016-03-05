package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {
	private GameStates currentGameState;
	
	public GameStateManager(GameStates gs) {
		currentGameState = gs;
		currentGameState.getGameState().changeTo(this);
	}
	
	public void render(SpriteBatch batch) {
		currentGameState.getGameState().render(batch);
	}
	
	public void update(float delta) {
		currentGameState.getGameState().update(delta);
	}
	
	public void changeGameState(GameStates gm) {
		currentGameState.getGameState().changeFrom();
		currentGameState = gm;
		currentGameState.getGameState().changeTo(this);
	}
	
	public void resize(float width, float height) {
		currentGameState.getGameState().resize(width, height);
	}
}