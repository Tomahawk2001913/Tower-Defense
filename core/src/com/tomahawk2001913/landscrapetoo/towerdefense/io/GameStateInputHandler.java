package com.tomahawk2001913.landscrapetoo.towerdefense.io;

import com.badlogic.gdx.InputProcessor;
import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.GameState;
import com.tomahawk2001913.landscrapetoo.towerdefense.screens.GameScreen;

public class GameStateInputHandler implements InputProcessor {
	private GameState gs;
	
	public GameStateInputHandler(GameState gs) {
		this.gs = gs;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return gs.touchDown(screenX * GameScreen.scale, screenY * GameScreen.scale);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return gs.touchUp(screenX * GameScreen.scale, screenY * GameScreen.scale);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return gs.touchDragged(screenX * GameScreen.scale, screenY * GameScreen.scale);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		return false;
	}
}