package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PanelCluster extends Panel {
	private Panel[] panels;
	
	public PanelCluster(Vector2 location, Panel panels[]) {
		super(location, 0, 0, true);
		
		this.panels = panels;
		
		moved();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		for(Panel panel : panels) {
			panel.render(batch);
		}
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		for(Panel panel : panels) {
			panel.update(delta);
		}
		
		if(!getLocation().equals(panels[0].getLocation())) {
			moved();
		}
	}
	
	@Override
	public void moved() {
		if(panels == null) return;
		
		float currentWidth = 0, currentHeight = 0;
		
		for(int i = 0; i < panels.length; i++) {
			panels[i].setLocation(getLocation().x + currentWidth, getLocation().y);
			panels[i].moved();
			currentWidth += panels[i].getWidth();
			if(panels[i].getHeight() > currentHeight) currentHeight = panels[i].getHeight();
		}
		
		setWidth(currentWidth);
		setHeight(currentHeight);
	}
	
	public Panel[] getPanels() {
		return panels;
	}
	
	public void setPanels(Panel[] panels) {
		this.panels = panels;
		moved();
	}
	
	@Override
	public boolean touchDown(float x, float y) {
		for(Panel panel : panels) {
			if(panel.touchDown(x, y)) return true;
		}
		
		return super.touchDown(x, y);
	}
	
	@Override
	public boolean touchUp(float x, float y) {
		boolean result = false;
		
		for(Panel panel : panels) {
			if(panel.touchUp(x, y)) result = true;
		}
		
		if(super.touchUp(x, y)) result = true;
		
		return result;
	}
	
	@Override
	public boolean touchDragged(float x, float y)  {
		for(Panel panel : panels) {
			if(panel.touchDragged(x, y)) return true;
		}
		
		return super.touchDragged(x, y);
	}
}