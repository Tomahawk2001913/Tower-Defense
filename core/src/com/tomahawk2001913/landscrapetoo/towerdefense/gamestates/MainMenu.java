package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tomahawk2001913.landscrapetoo.towerdefense.screens.GameScreen;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.PlayButton;

public class MainMenu extends GameState {
	private PlayButton pb;
	
	public MainMenu() {
		pb = new PlayButton(0, 0, 20, 20, this);
	}
	
	@Override
	public void changeTo(GameStateManager superManager) {
		super.changeTo(superManager);
		resize(GameScreen.gameWidth, GameScreen.gameHeight);
	}
	
	@Override
	public void changeFrom() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		pb.render(batch);
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