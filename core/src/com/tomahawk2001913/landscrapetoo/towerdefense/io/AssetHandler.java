package com.tomahawk2001913.landscrapetoo.towerdefense.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.GrassTile;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.Tile;

public class AssetHandler {
	public static Texture texture, tiles;
	
	public static TextureRegion button;
	
	// Tiles
	public static TextureRegion grassTile;
	
	public static void create() {
		texture = new Texture(Gdx.files.internal("Textures/Texture.png"));
		
		button = new TextureRegion(texture, 0, 0, 7, 7);
		
		// Temporary grass loading.
		grassTile = new TextureRegion(new Texture(Gdx.files.internal("Textures/grasstile.png")));
	}
	
	public static Tile[][] loadMap(String internalPath) {
		try {
			BufferedReader br = new BufferedReader(Gdx.files.internal(internalPath).reader());
			String currentLine;
			
			ArrayList<ArrayList<Tile>> tilesList = new ArrayList<ArrayList<Tile>>();
			
			while((currentLine = br.readLine()) != null) {
				if(currentLine.isEmpty()) continue;
				
				ArrayList<Tile> tileColumn = new ArrayList<Tile>();
				
				String values[] = currentLine.trim().split(" ");
				for(String string : values) {
					if(!string.isEmpty()) {
						int id = Integer.parseInt(string);
						Gdx.app.log("AssetHandler", "" + id);
						switch(id) {
						case 1: tileColumn.add(new GrassTile());
						}
					}
				}
				tilesList.add(tileColumn);
			}
			
			Tile tiles[][] = new Tile[tilesList.get(0).size()][tilesList.size()];
			for(int x = 0; x < tiles[0].length; x++) {
				for(int y = 0; y < tiles.length; y++) {
					tiles[y][x] = tilesList.get(x).get(y);
				}
			}
			return tiles;
			
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void dispose() {
		texture.dispose();
	}
}