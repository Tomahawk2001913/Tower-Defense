package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TextPanel extends Panel {
	private List<Text> texts;
	private float lineGap;
	
	public TextPanel(Vector2 location, float width, float height, boolean moveable, float lineGap, HashMap<String, Integer> text) {
		super(location, width, height, moveable);
		
		texts = new ArrayList<Text>();
		
		for(String line : text.keySet()) {
			texts.add(new Text(line, text.get(line), 0, 0));
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		
		for(Text text : texts) {
			text.render(batch);
		}
	}
	
	@Override
	public void moved() {
		for(int i = 0; i < texts.size(); i++) {
			// Add something.
		}
	}
}