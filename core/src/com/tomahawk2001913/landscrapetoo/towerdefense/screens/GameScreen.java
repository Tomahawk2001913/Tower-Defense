package com.tomahawk2001913.landscrapetoo.towerdefense.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.GameStateManager;
import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.GameStates;

public class GameScreen implements Screen {
	private GameStateManager gsm;
	
	public GameScreen() {
		Gdx.app.log("GameScreen", "Created.");
		gsm = new GameStateManager(GameStates.MAINMENU);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gsm.render();
		gsm.update(delta);
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