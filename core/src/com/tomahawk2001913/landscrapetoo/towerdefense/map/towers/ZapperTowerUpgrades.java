package com.tomahawk2001913.landscrapetoo.towerdefense.map.towers;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.Text;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.TextPanel;

public enum ZapperTowerUpgrades implements TowerUpgrades {
	ORIGINAL(20, 2, 0, 1, "Original... Why are you seeing this?"), 
	HIGHPOWER(35, 2, 200, 10, "Zap 10 robots at once!");
	
	private float damage, fireRate;
	private int price, robots;
	private TextPanel information;
	
	private ZapperTowerUpgrades(float damage, float fireRate, int price, int robots, String name) {
		this.damage = damage;
		this.fireRate = fireRate;
		this.price = price;
		this.robots = robots;
		
		List<Text> texts = Tower.towerInformationBuilder(name, damage, fireRate, price);
		
		information = new TextPanel(new Vector2(0, 0), 0, 0, false, 4, 10, texts);
	}
	
	@Override
	public float getDamage() {
		return damage;
	}
	
	@Override
	public float getFireRate() {
		return fireRate;
	}
	
	@Override
	public int getPrice() {
		return price;
	}
	
	public int getRobotsAtOnce() {
		return robots;
	}
	
	@Override
	public TextPanel getInformation() {
		return information;
	}
}