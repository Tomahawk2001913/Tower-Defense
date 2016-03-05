package com.tomahawk2001913.landscrapetoo.towerdefense.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
	public GameScreen() {
		Gdx.app.log("GameScreen", "Created.");
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.app.log("GameScreen", "render(" + delta + ")");
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}
}