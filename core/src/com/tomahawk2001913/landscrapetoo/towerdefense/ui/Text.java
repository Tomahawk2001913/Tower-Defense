package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;

public class Text {
	private Vector2 location;
	private BitmapFont font;
	private String content;
	private GlyphLayout layout;
	
	public Text(String content, int size, float x, float y) {
		location = new Vector2(x, y);
		
		this.content = content;
		
		if(!AssetHandler.fontExists(size)) AssetHandler.generateFont(size);
		font = AssetHandler.getFont(size);
		
		layout = new GlyphLayout(font, content);
	}
	
	public void render(SpriteBatch batch) {
		font.draw(batch, content, location.x, location.y);
	}
	
	public void setLocation(float x, float y) {
		location.set(x, y);
	}
	
	public void setText(String text) {
		content = text;
		layout.setText(font, text);
	}
	
	public String getText() {
		return content;
	}
	
	public Vector2 getLocation() {
		return location;
	}
	
	public float getWidth() {
		return layout.width;
	}
	
	public float getHeight() {
		return layout.height;
	}
}