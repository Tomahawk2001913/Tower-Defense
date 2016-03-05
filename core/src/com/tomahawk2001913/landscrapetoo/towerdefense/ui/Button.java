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
	
	public void setLocation(float x, float y) {
		super.setPosition(x, y);
	}
	
	public void setSize(float width, float height) {
		super.setSize(width, height);
	}
	
	public boolean touchDown(float x, float y) {
		if(super.getBoundingRectangle().contains(x, y)) {
			pressed = true;
			return true;
		}
		
		return false;
	}
	
	public boolean touchUp(float x, float y) {
		pressed = false;
		
		if(super.getBoundingRectangle().contains(x, y) && pressed) {
			action();
			return true;
		}
		
		return false;
	}
	
	public abstract void action();
}