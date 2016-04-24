package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ButtonPanel extends Panel {
	// Constants
	public static final float BORDER = 4.5f;
	
	private Button button;
	
	public ButtonPanel(Vector2 location, boolean moveable, Button button) {
		super(location, button.getWidth() + BORDER * 2, button.getHeight() + BORDER * 2 , moveable);
		this.button = button;
		
		moved();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		button.render(batch);
	}

	@Override
	public void moved() {
		if(button == null) return;
		
		button.setLocation(getLocation().x + BORDER, getLocation().y + BORDER);
	}
	
	@Override
	public boolean touchUp(float x, float y) {
		if(!button.touchUp(x, y)) {
			return super.touchUp(x, y);
		} else return true;
	}
	
	@Override
	public boolean touchDown(float x, float y) {
		if(!button.touchDown(x, y)) {
			return super.touchDown(x, y);
		} else return true;
	}
}