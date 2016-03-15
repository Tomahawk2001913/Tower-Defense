package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;

public abstract class Panel {
	private TextureRegion bg;
	
	private Vector2 location;
	private Rectangle bounds;
	
	private float width, height, xTouchOffset, yTouchOffset;
	
	private boolean touched;
	
	public Panel(Vector2 location, float width, float height) {
		this.location = location;
		bounds = new Rectangle(location.x, location.y, width, height);
		
		this.width = width;
		this.height = height;
		
		xTouchOffset = 0;
		yTouchOffset = 0;
		
		touched = false;
		
		this.bg = AssetHandler.panelBg;
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(bg, location.x, location.y, width, height);
	}
	
	public void update(float delta) {
		bounds.set(location.x, location.y, width , height);
		
		if(location.x < 0) location.x = 0;
		if(location.y < 0) location.y = 0;
	}
	
	public boolean touchDown(float x, float y) {
		if(bounds.contains(x, y)) {
			touched = true;
			
			xTouchOffset = x - location.x;
			yTouchOffset = y - location.y;
			
			return true;
		}
		return false;
	}
	
	public boolean touchUp(float x, float y) {
		touched = false;
		
		xTouchOffset = 0;
		yTouchOffset = 0;
		
		return false;
	}
	
	public boolean touchDragged(float x, float y) {
		if(touched && bounds.contains(x, y)) {
			location.set(x + xTouchOffset, y + yTouchOffset);
			return true;
		}
		
		return false;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}