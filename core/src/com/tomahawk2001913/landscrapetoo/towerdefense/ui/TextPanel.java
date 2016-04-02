package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TextPanel extends Panel {
	private List<Text> texts;
	private float lineGap, border;
	
	public TextPanel(Vector2 location, float width, float height, boolean moveable, float lineGap, float border, List<Text> texts) {
		super(location, width, height, moveable);
		
		this.lineGap = lineGap;
		this.border = border;
		
		this.texts = texts;
		
		moved();
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
		if(texts == null) return;
		
		setWidth(0);
		setHeight(0);
		
		for(int i = 0; i < texts.size(); i++) {
			Text temp = texts.get(i);
			temp.setLocation(getLocation().x + border, getLocation().y + border + (lineGap + temp.getHeight()) * i);
			
			float testWidth = temp.getWidth(), testHeight = (lineGap + temp.getHeight()) * (i + 1);
			
			if(testWidth > getWidth())
				setWidth(testWidth + border * 2);
			
			if(testHeight > getHeight()) 
				setHeight(testHeight + border * 2);
		}
		// TODO: Fix stuff here! //
	}
}