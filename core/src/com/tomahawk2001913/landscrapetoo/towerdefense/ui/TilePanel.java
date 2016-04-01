package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.Tiles;

public class TilePanel extends Panel {
	private List<TilesData> tiles;
	
	private TileMap tm;
	
	// Constants.
	public static final float TILESPERROW = 2;
	
	public TilePanel(Vector2 location, List<Tiles> tiles, TileMap tm, boolean moveable) {
		super(location, TileMap.TILE_DIMENSION * 1.5f, TileMap.TILE_DIMENSION * 1.5f, moveable);
		
		this.tiles = new ArrayList<TilesData>();
		
		for(Tiles tile : tiles) {
			this.tiles.add(new TilesData(new Vector2(0, 0), tile));
		}
		
		this.tm = tm;
		
		moved();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		
		for(TilesData tile : tiles) {
			tile.render(batch);
		}
	}
	
	@Override
	public void moved() {
		if(tiles == null) return;
		
		int i = 0;
		int row = 0;
		
		for(TilesData tile: tiles) {
			tile.setLocation(getLocation().x + TileMap.TILE_DIMENSION * 0.25f + TileMap.TILE_DIMENSION * i, getLocation().y + TileMap.TILE_DIMENSION * 0.25f + TileMap.TILE_DIMENSION * row);
			
			i++;
			if(i >= TILESPERROW) {
				i -= 3;
				row++;
			}
		}
	}
	
	@Override
	public boolean touchDown(float x, float y) {
		for(TilesData tile : tiles) {
			if(tile.touchDown(x, y)) return true;
		}
		
		return super.touchDown(x, y);
	}
	
	@Override
	public boolean touchUp(float x, float y) {
		boolean returnTrue = false;
		
		for(TilesData tile : tiles) {
			if(tile.touchUp(x, y)) returnTrue = true;
		}
		
		if(returnTrue) return true;
		else return super.touchUp(x, y);
	}
	
	@Override
	public boolean touchDragged(float x, float y) {
		for(TilesData tile : tiles) {
			if(tile.touchDragged(x, y)) return true;
		}
		
		return super.touchDragged(x, y);
	}
	
	private class TilesData {
		public Tiles type;
		
		public Rectangle bounds;
		
		public boolean touched, place;
		
		public float xTouchOffset, yTouchOffset;
		
		public TilesData(Vector2 location, Tiles type) {
			this.type = type;
			
			bounds = new Rectangle(location.x, location.y, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
			
			touched = false;
			place = false;
			
			xTouchOffset = 0;
			yTouchOffset = 0;
		}
		
		public void render(SpriteBatch batch) {
			batch.draw(type.getTextureRegion(), bounds.x, bounds.y, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
		}
		
		public boolean touchDown(float x, float y) {
			if(bounds.contains(x, y)) {
				touched = true;
				
				xTouchOffset = bounds.x - x;
				yTouchOffset = bounds.y - y;
				
				return true;
			}
			
			return false;
		}
		
		public boolean touchUp(float x, float y) {
			touched = false;
			
			xTouchOffset = 0;
			yTouchOffset = 0;
			
			if(bounds.contains(x, y)) {
				if(getPlace()) {
					tm.replaceTile((int) ((x - tm.getXOffset()) / TileMap.TILE_DIMENSION), (int) ((y - tm.getYOffset()) / TileMap.TILE_DIMENSION), type);
					place = false;
				}
				
				moved();
				
				return true;
			}
			
			place = false;
			
			return false;
		}
		
		public boolean touchDragged(float x, float y) {
			if(touched) {
				bounds.set(x + xTouchOffset, y + yTouchOffset, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
				place = true;
				return true;
			}
			
			return false;
		}
		
		public void setLocation(float x, float y) {
			bounds.set(x, y, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
		}
		
		public boolean getPlace() { 
			return place;
		}
	}
}