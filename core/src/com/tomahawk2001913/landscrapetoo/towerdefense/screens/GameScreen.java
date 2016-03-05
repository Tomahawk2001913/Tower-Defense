package com.tomahawk2001913.landscrapetoo.towerdefense.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.GameStateManager;
import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.GameStates;

public class GameScreen implements Screen {
	private GameStateManager gsm;
	
	// Constants
	public static final float optimalGameHeight = 350.0f;
	
	private float scale, gameHeight, gameWidth;
	
	public GameScreen() {
		Gdx.app.log("GameScreen", "Created.");
		gsm = new GameStateManager(GameStates.MAINMENU);
	}
	
	@Override
	public void show() {
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gsm.render();
		gsm.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		gameHeight = optimalGameHeight;
		scale = gameHeight / Gdx.graphics.getHeight();
		gameWidth = Gdx.graphics.getWidth() * scale;
		
		Gdx.app.log("GameScreen", "Resized to a width/height/scale of " + gameWidth + "/" + gameHeight + "/" + scale);
		
		gsm.resize(gameWidth, gameHeight);
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
