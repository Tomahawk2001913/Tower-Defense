package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;

public class NextWaveButton extends Button {
	public NextWaveButton(float x, float y, float width, float height, boolean show, TextureRegion img) {
		super(x, y, width, height, show, img);
	}
	
	@Override
	public void action() {
		TileMap.nextWave();
	}
}