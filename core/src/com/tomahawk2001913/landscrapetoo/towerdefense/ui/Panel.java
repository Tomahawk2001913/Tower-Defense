package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.Color;
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
	
	// Constants.
	public static final float ALPHA = 0.85f;
	
	private boolean touched, moveable;
	
	public Panel(Vector2 location, float width, float height, boolean moveable) {
		this.location = location;
		bounds = new Rectangle(location.x, location.y, width, height);
		
		this.width = width;
		this.height = height;
		
		this.moveable = moveable;
		
		xTouchOffset = 0;
		yTouchOffset = 0;
		
		touched = false;
		
		moved();
		
		this.bg = AssetHandler.panelBg;
	}
	
	public void render(SpriteBatch batch) {
		Color tempColor = batch.getColor();
		batch.setColor(1, 1, 1, ALPHA);
		batch.draw(bg, location.x, location.y, width, height);
		batch.setColor(tempColor);
	}
	
	public void update(float delta) {
		bounds.set(location.x, location.y, width , height);
		
		if(location.x < 0) location.x = 0;
		if(location.y < 0) location.y = 0;
	}
	
	public boolean touchDown(float x, float y) {
		if(moveable && bounds.contains(x, y)) {
			touched = true;
			xTouchOffset = location.x - x;
			yTouchOffset = location.y - y;
			
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
		if(touched) {
			location.set(x + xTouchOffset, y + yTouchOffset);
			moved();
			return true;
		}
		
		return false;
	}
	
	public abstract void moved();
	
	public Vector2 getLocation() {
		return location;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}