package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

public class GameStateManager {
	private GameStates currentGameState;
	
	public GameStateManager(GameStates gm) {
		currentGameState = gm;
		currentGameState.getGameState().changeTo();
	}
	
	public void render() {
		currentGameState.getGameState().render();
	}
	
	public void update(float delta) {
		currentGameState.getGameState().update(delta);
	}
	
	public void changeGameState(GameStates gm) {
		currentGameState.getGameState().changeFrom();
		currentGameState = gm;
		currentGameState.getGameState().changeTo();
	}
}