package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.MainMenu;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;

public class PlayButton extends Button {
	private MainMenu mm;
	
	public PlayButton(float x, float y, float width, float height, MainMenu mm) {
		super(x, y, width, height, true, AssetHandler.button);
		
		this.mm = mm;
	}

	@Override
	public void action() {
		mm.getCurrentGameStateManager();
	}
}