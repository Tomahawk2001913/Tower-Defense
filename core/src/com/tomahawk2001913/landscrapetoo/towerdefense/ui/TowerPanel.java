package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.Playing;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.Tiles;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.GatlingCannonTower;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.Tower;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.ZapperTower;

public class TowerPanel extends Panel {
	private enum Towers {
		GATLINGCANNON(AssetHandler.gatlingCannonTower, new GatlingCannonTower(new Vector2(0, 0), null)), 
		ZAPPER(AssetHandler.zapperTower, new ZapperTower(new Vector2(0, 0), null));
		
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
		
		public int getPrice() {
			return tower.getPrice();
		}
		
		public float getDamage() {
			return tower.getDamage();
		}
		
		public float getFireRate() {
			return tower.getFireRate();
		}
		
		public String getName() {
			return tower.getName();
		}
	}
	
	private TileMap tm;
	private Playing playing;
	
	// Constants.
	public static final float WIDTH = 80, HEIGHT = 120, TOWERS_PER_ROW = 3;
	
	public TowerPanel(Vector2 location, TileMap tm, Playing playing, boolean moveable) {
		super(location, WIDTH, HEIGHT, moveable);
		
		this.tm = tm;
		
		this.playing = playing;
		
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
			if(tower.getTouched()) {
				List<Text> info = new ArrayList<Text>();
				info.add(new Text(tower.getName(), 18, 0, 0));
				info.add(new Text("Dmg: " + tower.getDamage(), 16, 0, 0));
				info.add(new Text("Spd: " + tower.getFireRate(), 16, 0, 0));
				info.add(new Text("$" + tower.getPrice(), 16, 0, 0));
				
				playing.setInformationPanelInfo(info);
			}
			
			tower.setTouched(false);
			
			tower.setXTouchOffset(0);
			tower.setYTouchOffset(0);
			
			if(tower.getBounds().contains(x, y)) {
				returnTrue = true;
				if(tower.getPlace()) {
					if(!super.getBounds().contains(x, y)) {
						int tX =  (int) ((x - tm.getXOffset()) / TileMap.TILE_DIMENSION), tY = (int) ((y - tm.getYOffset()) / TileMap.TILE_DIMENSION);
						
						if(tm.getTile(tX, tY) != Tiles.BARRIER) {
							moved();
							return true;
						}
						
						Tower towerToAdd = tower.getTower().copy();
						towerToAdd.setLocation(tX * TileMap.TILE_DIMENSION, tY * TileMap.TILE_DIMENSION);
						
						if(Playing.hasMoney(tower.getPrice()) && tm.placeTopTile(tX , tY , towerToAdd)) {
							Playing.subtractMoney(tower.getPrice());
						}
					}
					
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