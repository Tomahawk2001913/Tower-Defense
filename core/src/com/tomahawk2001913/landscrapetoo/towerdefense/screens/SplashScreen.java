package com.tomahawk2001913.landscrapetoo.towerdefense.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tomahawk2001913.landscrapetoo.towerdefense.TowerDefenseMain;

public class SplashScreen implements Screen {
	private TowerDefenseMain tdm;
	
	public SplashScreen(TowerDefenseMain tdm) {
		this.tdm = tdm;
	}
	
	@Override
	public void show() {
		Gdx.app.log("SplashScreen", "show()");
	}

	@Override
	public void render(float delta) {
		tdm.setScreen(new GameScreen());
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