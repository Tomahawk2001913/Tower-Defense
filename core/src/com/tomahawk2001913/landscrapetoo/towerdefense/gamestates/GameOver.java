package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver extends GameState {
	public GameOver() {
		
	}
	
	@Override
	public void changeTo(GameStateManager gsm) {
		super.changeTo(gsm);
		
		getCurrentGameStateManager().changeGameState(GameStates.MAINMENU);
	}
	
	@Override
	public void changeFrom() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		
	}

	@Override
	public void update(float delta) {
		
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
