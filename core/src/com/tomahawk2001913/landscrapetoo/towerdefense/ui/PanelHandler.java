package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PanelHandler {
	private List<Panel> panels, removePanels;
	
	public PanelHandler() {
		panels = new ArrayList<Panel>();
		removePanels = new ArrayList<Panel>();
	}
	
	public void render(SpriteBatch batch) {
		for(Panel panel : panels) {
			panel.render(batch);
		}
	}
	
	public void update(float delta) {
		for(Panel panel : panels) {
			panel.update(delta);
		}
		
		for(Panel panel : removePanels) {
			panels.remove(panel);
		}
		
		removePanels.clear();
	}
	
	public boolean touchDown(float x, float y) {
		for(Panel panel : panels) {
			if(panel.touchDown(x, y)) return true;
		}
		
		return false;
	}
	
	public boolean touchUp(float x, float y) {
		for(Panel panel : panels) {
			if(panel.touchUp(x, y)) return true;
		}
		
		return false;
	}
	
	public boolean touchDragged(float x, float y) {
		for(Panel panel : panels) {
			if(panel.touchDragged(x, y)) return true;
		}
		
		return false;
	}
	
	public void addPanel(Panel panel) {
		panels.add(panel);
	}
	
	public void removePanel(Panel panel) {
		removePanels.add(panel);
	}
	
	public void setToTop(Panel panel) {
		Panel temp = panels.get(panels.indexOf(panel));
		
		panels.remove(temp);
		panels.add(temp);
	}
}