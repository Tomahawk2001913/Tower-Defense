package com.tomahawk2001913.landscrapetoo.towerdefense.io;

import com.badlogic.gdx.InputProcessor;
import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.MainMenu;
import com.tomahawk2001913.landscrapetoo.towerdefense.screens.GameScreen;

public class MainMenuInputHandler implements InputProcessor {
	private MainMenu mm;
	
	public MainMenuInputHandler(MainMenu mm) {
		this.mm = mm;
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
		return mm.touchDown(screenX * GameScreen.scale, screenY * GameScreen.scale);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return mm.touchUp(screenX * GameScreen.scale, screenY * GameScreen.scale);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
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