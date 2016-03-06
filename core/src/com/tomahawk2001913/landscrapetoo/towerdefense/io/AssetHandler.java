package com.tomahawk2001913.landscrapetoo.towerdefense.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.AnimatedGrassTile;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.Tiles;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TopTile;

public class AssetHandler {
	public static Texture texture, tiles;
	
	public static TextureRegion button;
	
	// Tiles
	public static TextureRegion grassTile, stoneTile, sandTile, waterTile, dirtTile;
	
	// Animated grass!
	public static TextureRegion grass1, grass2, grass3;
	public static Animation animatedGrass;
	
	public static void create() {
		// Load everything-texture.
		texture = new Texture(Gdx.files.internal("Textures/Texture.png"));
		
		// Load tiles.
		grassTile = new TextureRegion(texture, 0, 0, 16, 16);
		stoneTile = new TextureRegion(texture, 16, 0, 16, 16);
		sandTile = new TextureRegion(texture, 32, 0, 16, 16);
		waterTile = new TextureRegion(texture, 48, 0, 16, 16);
		dirtTile = new TextureRegion(texture, 64, 0, 16, 16);
		
		// Load animated grass!
		grass1 = new TextureRegion(texture, 0, 16, 16, 16);
		grass1.flip(false, true);
		grass2 = new TextureRegion(texture, 16, 16, 16, 16);
		grass2.flip(false, true);
		grass3 = new TextureRegion(texture, 32, 16, 16, 16);
		grass3.flip(false, true);
		
		animatedGrass = new Animation(0.3f, new TextureRegion[] {grass1, grass2, grass3});
		animatedGrass.setPlayMode(PlayMode.LOOP_PINGPONG);
	}
	
	public static TileMap loadMap(String internalPath) {
		try {
			BufferedReader br = new BufferedReader(Gdx.files.internal(internalPath).reader());
			String currentLine;
			
			ArrayList<ArrayList<Tiles>> tilesList = new ArrayList<ArrayList<Tiles>>();
			ArrayList<ArrayList<TopTile>> topList = new ArrayList<ArrayList<TopTile>>();
			
			while((currentLine = br.readLine()) != null) {
				if(currentLine.isEmpty()) continue;
				
				ArrayList<Tiles> tileColumn = new ArrayList<Tiles>();
				ArrayList<TopTile> topColumn = new ArrayList<TopTile>();
				
				String values[] = currentLine.trim().split(" ");
				for(String string : values) {
					if(!string.isEmpty()) {
						int id = Integer.parseInt(string);
						switch(id) {
						case 1:  {
							tileColumn.add(Tiles.GRASS);
							topColumn.add(null);
							break;
						}
						case 2: {
							topColumn.add(new AnimatedGrassTile());
							tileColumn.add(Tiles.GRASS);
							break;
						}
						}
					}
				}
				tilesList.add(tileColumn);
				topList.add(topColumn);
			}
			
			Tiles tiles[][] = new Tiles[tilesList.get(0).size()][tilesList.size()];
			TopTile tops[][] = new TopTile[tiles.length][tiles[0].length];
			for(int x = 0; x < tiles[0].length; x++) {
				for(int y = 0; y < tiles.length; y++) {
					tiles[y][x] = tilesList.get(x).get(y);
					tops[y][x] = topList.get(x).get(y);
				}
			}
			
			return new TileMap(tiles, tops);
			
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void dispose() {
		texture.dispose();
	}
}