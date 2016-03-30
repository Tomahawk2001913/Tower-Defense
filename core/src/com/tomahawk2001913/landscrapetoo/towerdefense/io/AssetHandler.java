package com.tomahawk2001913.landscrapetoo.towerdefense.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.AnimatedGrassTile;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.Base;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.PineTree;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.RobotSpawner;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.Tiles;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TopTile;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.Tree;

public class AssetHandler {
	public static Texture texture, tiles;
	
	// UI
	public static TextureRegion panelBg;
	
	// Tiles
	public static TextureRegion grassTile, stoneTile, sandTile, waterTile, dirtTile, snowTile, barrierTile;
	
	// Top Tiles
	public static TextureRegion tree, pineTree, robotSpawn;
	
	// Animated grass!
	public static TextureRegion grass1, grass2, grass3;
	public static Animation animatedGrass;
	
	// Tower stuff
	public static TextureRegion gatlingCannonTower, gatlingCannonTowerShooting, laserCannonTower, laserCannonTowerShooting, zapperTower, zapperTowerShooting; 
	public static TextureRegion laserBeam, zapperBolt;
	public static Animation gatlingCannonTowerShootingAnimation;
	
	// Entities
	public static TextureRegion healthBarColor, robotInfantry1, robotInfantry2, drillBot;
	public static Animation robotInfantryAnimation;
	
	// Base
	public static TextureRegion factory1, factory2;
	public static Animation factoryAnimation;
	
	public static void create() {
		// Load everything-texture.
		texture = new Texture(Gdx.files.internal("Textures/Texture.png"));
		
		// Load UI.
		panelBg = new TextureRegion(texture, 51, 53, 1, 1);
		
		// Load tiles.
		grassTile = new TextureRegion(texture, 0, 0, 16, 16);
		stoneTile = new TextureRegion(texture, 16, 0, 16, 16);
		sandTile = new TextureRegion(texture, 32, 0, 16, 16);
		waterTile = new TextureRegion(texture, 48, 0, 16, 16);
		dirtTile = new TextureRegion(texture, 64, 0, 16, 16);
		snowTile = new TextureRegion(texture, 80, 0, 16, 16);
		barrierTile = new TextureRegion(texture, 112, 0, 16, 16);
		
		// Load top tiles.
		tree = new TextureRegion(texture, 48, 16, 16, 16);
		tree.flip(false, true);
		pineTree = new TextureRegion(texture, 64, 16, 16, 16);
		pineTree.flip(false, true);
		robotSpawn = new TextureRegion(texture, 96, 0, 16, 16);
		
		// Load animated grass!
		grass1 = new TextureRegion(texture, 0, 16, 16, 16);
		grass1.flip(false, true);
		grass2 = new TextureRegion(texture, 16, 16, 16, 16);
		grass2.flip(false, true);
		grass3 = new TextureRegion(texture, 32, 16, 16, 16);
		grass3.flip(false, true);
		
		// Load tower stuff
		gatlingCannonTower = new TextureRegion(texture, 80, 16, 16, 16);
		gatlingCannonTower.flip(false, true);
		gatlingCannonTowerShooting = new TextureRegion(texture, 96, 16, 16, 16);
		gatlingCannonTowerShooting.flip(false, true);
		
		gatlingCannonTowerShootingAnimation = new Animation(0.06f, new TextureRegion[] {gatlingCannonTower, gatlingCannonTowerShooting});
		gatlingCannonTowerShootingAnimation.setPlayMode(PlayMode.LOOP);
		
		laserCannonTower = new TextureRegion(texture, 0, 32, 16, 16);
		laserCannonTower.flip(false, true);
		laserCannonTowerShooting = new TextureRegion(texture, 16, 32, 16, 16);
		laserCannonTowerShooting.flip(false, true);
		
		zapperTower = new TextureRegion(texture, 32, 32, 16, 16);
		zapperTower.flip(false, true);
		zapperTowerShooting = new TextureRegion(texture, 48, 32, 16, 16);
		zapperTowerShooting.flip(false, true);
		
		laserBeam = new TextureRegion(texture, 112, 16, 16, 16);
		laserBeam.flip(false, true);
		
		zapperBolt = new TextureRegion(texture, 64, 32, 16, 16);
		zapperBolt.flip(false, true);
		
		// Load health bar color
		healthBarColor = new TextureRegion(texture, 50, 53, 1, 1);
		
		// Load entity stuff
		robotInfantry1 = new TextureRegion(texture, 0, 48, 16, 16);
		robotInfantry1.flip(false, true);
		robotInfantry2 = new TextureRegion(texture, 16, 48, 16, 16);
		robotInfantry2.flip(false, true);
		
		robotInfantryAnimation = new Animation(0.35f, new TextureRegion[] {robotInfantry1, robotInfantry2});
		robotInfantryAnimation.setPlayMode(PlayMode.LOOP);
		
		drillBot = new TextureRegion(texture, 32, 48, 16, 16);
		drillBot.flip(false, true);
		
		// Load base stuff
		factory1 = new TextureRegion(texture, 0, 64, 32, 32);
		factory1.flip(false, true);
		factory2 = new TextureRegion(texture, 32, 64, 32, 32);
		factory2.flip(false, true);
		
		factoryAnimation = new Animation(0.35f, new TextureRegion[] {factory1, factory2});
		factoryAnimation.setPlayMode(PlayMode.LOOP);
		
		// Create Animation for grass
		animatedGrass = new Animation(0.3f, new TextureRegion[] {grass1, grass2, grass3});
		animatedGrass.setPlayMode(PlayMode.LOOP_PINGPONG);
	}
	
	public static TileMap loadMap(String internalPath) {
		try {
			BufferedReader br = new BufferedReader(Gdx.files.internal(internalPath).reader());
			String currentLine;
			
			// Holds multiple columns.
			ArrayList<ArrayList<Tiles>> tilesList = new ArrayList<ArrayList<Tiles>>();
			ArrayList<ArrayList<TopTile>> topList = new ArrayList<ArrayList<TopTile>>();
			
			while((currentLine = br.readLine()) != null) {
				if(currentLine.isEmpty()) continue;
				
				// Holds a column of values.
				ArrayList<Tiles> tileColumn = new ArrayList<Tiles>();
				ArrayList<TopTile> topColumn = new ArrayList<TopTile>();
				
				String values[] = currentLine.trim().split(" ");
				for(String string : values) {
					if(!string.isEmpty()) {
						int id = Integer.parseInt(string);
						switch(id) {
						case 0: {
							tileColumn.add(Tiles.STONE);
							topColumn.add(new RobotSpawner(new Vector2(0, 0), 2));
							break;
						}
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
						case 3: {
							tileColumn.add(Tiles.STONE);
							topColumn.add(null);
							break;
						}
						case 4: {
							tileColumn.add(Tiles.SAND);
							topColumn.add(null);
							break;
						}
						case 5: {
							tileColumn.add(Tiles.WATER);
							topColumn.add(null);
							break;
						}
						case 6: {
							tileColumn.add(Tiles.DIRT);
							topColumn.add(null);
							break;
						}
						case 7: {
							tileColumn.add(Tiles.GRASS);
							topColumn.add(new Tree());
							break;
						}
						case 8: {
							tileColumn.add(Tiles.SNOW);
							topColumn.add(new PineTree());
							break;
						}
						case 9: {
							tileColumn.add(Tiles.BARRIER);
							topColumn.add(null);
							break;
						}
						case 10: {
							tileColumn.add(Tiles.GRASS);
							topColumn.add(new Base());
						}
						}
					}
				}
				// Adds the columns to the list.
				tilesList.add(tileColumn);
				topList.add(topColumn);
			}
			
			Tiles tiles[][] = new Tiles[tilesList.get(0).size()][tilesList.size()];
			TopTile tops[][] = new TopTile[tiles.length][tiles[0].length];
			// Translates ArrayLists into normal multidimensional arrays.
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