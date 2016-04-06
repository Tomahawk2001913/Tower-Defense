package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.GameStateInputHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.TileMap;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.Tiles;
import com.tomahawk2001913.landscrapetoo.towerdefense.screens.GameScreen;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.Panel;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.PanelHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.Text;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.TextPanel;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.TilePanel;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.TowerPanel;

public class Playing extends GameState {
	private TileMap tm;
	private GameStateInputHandler gsih;
	private PanelHandler ph;
	
	private static TextPanel moneyPanel;
	public static TextPanel informationPanel;
	
	public static int money;
	
	// Constants.
	public static final int STARTING_MONEY = 500;
	
	public Playing() {
		gsih = new GameStateInputHandler(this);
	}
	
	@Override
	public void changeTo(GameStateManager superManager) {
		super.changeTo(superManager);
		Gdx.input.setInputProcessor(gsih);
		tm = AssetHandler.loadMap("Maps/GrassyArea.tdm");
		
		money = STARTING_MONEY;
		
		List<Text> texts = new ArrayList<Text>();
		
		texts.add(new Text("$" + money, 26, 0, 0));
		
		moneyPanel = new TextPanel(new Vector2(0, 0), 0, 0, true, 4, 10, texts);
		
		informationPanel = new TextPanel(new Vector2(0, 0), 20, 10, true, 4, 10, new ArrayList<Text>() {{ add(new Text("Good luck!", 16, 0, 0)); }});
		informationPanel.setLocation(0, GameScreen.gameHeight - informationPanel.getHeight());
		
		List<Tiles> tiles = new ArrayList<Tiles>();
		
		tiles.add(Tiles.BARRIER);
		
		ph = new PanelHandler();
		ph.addPanel(new TowerPanel(new Vector2(GameScreen.gameWidth - TowerPanel.WIDTH, 0), this.tm, this, true));
		ph.addPanel(new TilePanel(new Vector2(GameScreen.gameWidth - TowerPanel.WIDTH, TowerPanel.HEIGHT), tiles, this.tm, true));
		ph.addPanel(moneyPanel);
		ph.addPanel(informationPanel);
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
	
	public void addPanel(Panel panel) {
		ph.addPanel(panel);
	}
	
	public void removePanel(Panel panel) {
		ph.removePanel(panel);
	}
	
	public void setInformationPanelInfo(List<Text> info) {
		informationPanel.setTexts(info);
	}
	
	public static void moneyChanged() {
		moneyPanel.setText("$" + money);
	}
	
	public static boolean hasMoney(int money) {
		return Playing.money >= money;
	}
	
	public static boolean subtractMoney(int money) {
		if(hasMoney(money)) {
			Playing.money -= money;
			moneyChanged();
			return true;
		}
		
		return false;
	}
	
	public static void addMoney(int money) {
		Playing.money += money;
		moneyChanged();
	}
}