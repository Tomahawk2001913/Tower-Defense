package com.tomahawk2001913.landscrapetoo.towerdefense.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetHandler {
	public static Texture texture;
	
	public static TextureRegion button;
	
	public static void create() {
		texture = new Texture(Gdx.files.internal("Textures/Texture.png"));
		
		button = new TextureRegion(texture, 0, 0, 7, 7);
	}
	
	public static void dispose() {
		texture.dispose();
	}
}