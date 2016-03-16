package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.GatlingCannonTower;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.Tower;

public class TowerPanel extends Panel {
	private enum Towers {
		GATLINGCANNON(AssetHandler.gatlingCannonTower, new GatlingCannonTower(new Vector2(0, 0), null));
		
		private TextureRegion img;
		private Tower tower;
		private Rectangle bounds;
		
		private float xTouchOffset, yTouchOffset;
		
		private boolean touched, place;
		
		private Towers(TextureRegion img, Tower tower) {
			this.img = img;
			this.tower = tower;
			bounds = new Rectangle();
			
			xTouchOffset = 0;
			yTouchOffset = 0;
			
			touched = false;
			place = false;
		}
		
		public void render(SpriteBatch batch) {
			batch.draw(img, bounds.x, bounds.y, bounds.width, bounds.height);
		}
		
		public Tower getTower() {
			return tower;
		}
		
		public void setBounds(float x, float y, float width, float height) {
			bounds.set(x, y, width, height);
		}
		
		public Rectangle getBounds() {
			return bounds;
		}
		
		public void setPlace(boolean place) {
			this.place = place;
		}
		
		public boolean getPlace() {
			return place;
		}
		
		public void setXTouchOffset(float xTouchOffset) {
			this.xTouchOffset = xTouchOffset;
		}
		
		public void setYTouchOffset(float yTouchOffset) {
			this.yTouchOffset = yTouchOffset;
		}
		
		public float getXTouchOffset() {
			return xTouchOffset;
		}
		
		public float getYTouchOffset() {
			return yTouchOffset;
		}
		
		public void setTouched(boolean touched) {
			this.touched = touched;
		}
		
		public boolean getTouched() {
			return touched;
		}
	}
	
	private TileMap tm;
	
	// Constants.
	public static final float WIDTH = 80, HEIGHT = 120, TOWERS_PER_ROW = 3;
	
	public TowerPanel(Vector2 location, TileMap tm) {
		super(location, WIDTH, HEIGHT);
		
		this.tm = tm;
		
		for(Towers tower : Towers.values()) {
			tower.getTower().setTileMap(tm);
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		
		for(Towers tower : Towers.values()) {
			tower.render(batch);
		}
	}
	
	@Override
	public boolean touchDown(float x, float y) {
		for(Towers tower : Towers.values()) {
			if(tower.getBounds().contains(x, y)) {
				tower.setTouched(true);
				tower.setXTouchOffset(tower.getBounds().x - x);
				tower.setYTouchOffset(tower.getBounds().y - y);
				return true;
			}
		}
		
		return super.touchDown(x, y);
	}
	
	@Override
	public boolean touchUp(float x, float y) {
		boolean returnTrue = false;
		
		for(Towers tower : Towers.values()) {
			tower.setTouched(false);
			
			tower.setXTouchOffset(0);
			tower.setYTouchOffset(0);
			
			if(tower.getBounds().contains(x, y)) {
				returnTrue = true;
				if(tower.getPlace()) {
					Tower towerToAdd = tower.getTower().copy();
					int tX =  (int) ((x - tm.getXOffset()) / TileMap.TILE_DIMENSION), tY = (int) ((y - tm.getYOffset()) / TileMap.TILE_DIMENSION);
					System.out.println(tX + " : " + tY);
					
					towerToAdd.setLocation(tX, tY);
					
					tm.placeTopTile(tX , tY , towerToAdd);
					moved();
				}
			}
		}
		
		if(returnTrue) return true;
		return super.touchUp(x, y);
	}
	
	@Override
	public boolean touchDragged(float x, float y) {
		for(Towers tower : Towers.values()) {
			if(tower.getTouched()) {
				tower.setBounds(x + tower.getXTouchOffset(), y + tower.getYTouchOffset(), tower.getBounds().width, tower.getBounds().height);
				tower.setPlace(true);
			}
		}
		
		return super.touchDragged(x, y);
	}
	
	@Override
	public void moved() {
		int i = 0, row = 0;
		for(Towers tower : Towers.values()) {
			if(i >= 3) {
				row++;
				i -= 3;
			}
			
			tower.setBounds(super.getLocation().x + TileMap.TILE_DIMENSION * i, super.getLocation().y + TileMap.TILE_DIMENSION * row, 
					TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
			
			i++;
		}
	}
}