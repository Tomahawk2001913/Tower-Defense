package com.tomahawk2001913.landscrapetoo.towerdefense.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	
	public static void dispose() {
		texture.dispose();
	}
}