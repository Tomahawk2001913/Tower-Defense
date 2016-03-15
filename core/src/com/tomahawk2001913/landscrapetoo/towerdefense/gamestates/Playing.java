package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.GameStateInputHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.RobotInfantry;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.GatlingCannonTower;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.PanelHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.TowerPanel;

public class Playing extends GameState {
	private TileMap tm;
	private GameStateInputHandler gsih;
	private PanelHandler ph;
	
	public Playing() {
		gsih = new GameStateInputHandler(this);
	}
	
	@Override
	public void changeTo(GameStateManager superManager) {
		super.changeTo(superManager);
		Gdx.input.setInputProcessor(gsih);
		tm = AssetHandler.loadMap("Maps/GrassyArea.tdm");
		tm.placeTopTile(0, 0, new GatlingCannonTower(new Vector2(0, 0), tm));
		
		tm.addEntity(new RobotInfantry(new Vector2(5, 2), tm, tm.findPath(new Vector2(5, 2), new Vector2(1, 1))));
		

		ph = new PanelHandler();
		ph.addPanel(new TowerPanel(new Vector2(20, 20), this.tm));
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
	}
	
	@Override
	public void resize(float width, float height) {
		
	}
	
	@Override
	public boolean touchDown(float x, float y) {
		return ph.touchDown(x, y);
	}
	
	@Override
	public boolean touchUp(float x, float y) {
		return ph.touchUp(x, y);
	}
	
	@Override
	public boolean touchDragged(float x, float y) {
		return ph.touchDragged(x, y);
	}
}