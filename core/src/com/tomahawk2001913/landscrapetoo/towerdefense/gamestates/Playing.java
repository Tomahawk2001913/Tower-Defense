package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.GameStateInputHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.Tiles;
import com.tomahawk2001913.landscrapetoo.towerdefense.screens.GameScreen;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.PanelHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.TextPanel;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.TilePanel;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.TowerPanel;

public class Playing extends GameState {
	private TileMap tm;
	private GameStateInputHandler gsih;
	private PanelHandler ph;
	
	private int money;
	
	public Playing() {
		gsih = new GameStateInputHandler(this);
	}
	
	@Override
	public void changeTo(GameStateManager superManager) {
		super.changeTo(superManager);
		Gdx.input.setInputProcessor(gsih);
		tm = AssetHandler.loadMap("Maps/GrassyArea.tdm");
		
		money = 0;
		
		List<Tiles> tiles = new ArrayList<Tiles>();
		
		tiles.add(Tiles.BARRIER);
		
		HashMap<String, Integer> texts = new HashMap<String, Integer>();
		
		texts.put("Test Text 1", 26);
		texts.put("Another text.", 26);
		
		ph = new PanelHandler();
		ph.addPanel(new TowerPanel(new Vector2(GameScreen.gameWidth - TowerPanel.WIDTH, 0), this.tm, true));
		ph.addPanel(new TilePanel(new Vector2(GameScreen.gameWidth - TowerPanel.WIDTH, TowerPanel.HEIGHT), tiles, this.tm, true));
		ph.addPanel(new TextPanel(new Vector2(0, 0), 0, 0, true, 2, 1, texts));
	}
	
	@Override
	public void changeFrom() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		tm.render(batch);
		ph.render(batch);
	}

	@Override
	public void update(float delta) {
		tm.update(delta);
		ph.update(delta);
		
		if(tm.isGameOver()) {
			getCurrentGameStateManager().changeGameState(GameStates.GAMEOVER);;
		}
	}
	
	@Override
	public void resize(float width, float height) {
		
	}
	
	@Override
	public boolean touchDown(float x, float y) {
		return ph.touchDown(x, y) || tm.touchDown(x, y);
	}
	
	@Override
	public boolean touchUp(float x, float y) {
		return ph.touchUp(x, y) || tm.touchUp(x, y);
	}
	
	@Override
	public boolean touchDragged(float x, float y) {
		return ph.touchDragged(x, y) || tm.touchDragged(x, y);
	}
}