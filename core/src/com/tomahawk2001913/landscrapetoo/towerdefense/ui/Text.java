package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;

public class Text {
	private Vector2 location;
	private BitmapFont font;
	private String content;
	
	public Text(String content, int size, float x, float y) {
		location = new Vector2(x, y);
		
		this.content = content;
		
		if(!AssetHandler.fontExists(size)) AssetHandler.generateFont(size);
		font = AssetHandler.getFont(size);
	}
	
	public void render(SpriteBatch batch) {
		font.draw(batch, content, location.x, location.y);
	}
	
	public float getWidth() {
		return 0;
	}
	
	public float getHeight() {
		return 0;
	}
}