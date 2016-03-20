package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

public enum GameStates {
	MAINMENU(new MainMenu()), PLAYING(new Playing()), GAMEOVER(new GameOver());
	
	private GameState gs;
	
	private GameStates(GameState gs) {
		this.gs = gs;
	}
	
	public GameState getGameState() {
		return gs;
	}
}