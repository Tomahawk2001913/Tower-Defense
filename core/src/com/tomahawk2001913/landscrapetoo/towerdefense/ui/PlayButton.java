package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.Gdx;
import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.GameStates;
import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.MainMenu;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;

public class PlayButton extends Button {
	private MainMenu mm;
	
	public PlayButton(float x, float y, float width, float height, MainMenu mm) {
		super(x, y, width, height, true, AssetHandler.stoneTile);
		
		this.mm = mm;
	}

	@Override
	public void action() {
		Gdx.app.log("PlayButton", "action()");
		mm.getCurrentGameStateManager().changeGameState(GameStates.PLAYING);
	}
}