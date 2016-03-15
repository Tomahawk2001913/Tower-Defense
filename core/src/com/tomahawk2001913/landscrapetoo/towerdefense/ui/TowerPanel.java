package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
		
		private Towers(TextureRegion img, Tower tower) {
			this.img = img;
			this.tower = tower;
		}
		
		public TextureRegion getTextureRegion() {
			return img;
		}
		
		public Tower getTower() {
			return tower;
		}
	}
	
	private List<Tower> towers;
	
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
		
		int i = 0, row = 0;
		for(Towers tower : Towers.values()) {
			i++;
			if(i >= 3) {
				row++;
				i -= 3;
			}
			
			batch.draw(tower.getTextureRegion(), super.getLocation().x, super.getLocation().y, TileMap.TILE_DIMENSION, TileMap.TILE_DIMENSION);
		}
	}
}