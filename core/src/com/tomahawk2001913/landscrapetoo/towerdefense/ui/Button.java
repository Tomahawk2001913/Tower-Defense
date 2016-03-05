package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Button extends Sprite {
	private boolean show, pressed;
	
	public Button(float x, float y, float width, float height, boolean show, TextureRegion img) {
		super(img);
		super.setPosition(x, y);
		super.setSize(width, height);
		this.show = show;
	}
	
	public void render(SpriteBatch batch) { 
		if(show) {
			if(pressed) {
				super.draw(batch, 0.6f);
			} else {
				super.draw(batch);
			}
		}
	}
	
	public abstract void action();
}