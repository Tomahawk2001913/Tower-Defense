package com.tomahawk2001913.landscrapetoo.towerdefense;

import com.badlogic.gdx.Game;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.screens.SplashScreen;

public class TowerDefenseMain extends Game {
	@Override
	public void create() {
		AssetHandler.create();
		setScreen(new SplashScreen(this));
	}
	
	@Override
	public void dispose() {
		AssetHandler.dispose();
	}
}