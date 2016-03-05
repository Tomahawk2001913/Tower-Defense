package com.tomahawk2001913.landscrapetoo.towerdefense.gamestates;

public abstract class GameState {
	public abstract void changeTo();
	
	public abstract void changeFrom();
	
	public abstract void render();
	
	public abstract void update(float delta);
	
	public abstract void resize(float width, float height);
}