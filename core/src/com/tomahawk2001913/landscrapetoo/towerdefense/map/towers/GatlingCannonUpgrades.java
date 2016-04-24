package com.tomahawk2001913.landscrapetoo.towerdefense.map.towers;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.Text;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.TextPanel;

public enum GatlingCannonUpgrades implements TowerUpgrades {
	ORIGINAL(20, 0.01f, 0, "Original... Why are you seeing this?"), 
	LEADBULLETS(35, 0.01f, 200, "Lead bullets!");
	
	private float damage, fireRate;
	private int price;
	private TextPanel information;
	
	private GatlingCannonUpgrades(float damage, float fireRate, int price, String name) {
		this.damage = damage;
		this.fireRate = fireRate;
		this.price = price;
		
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
	public float getPrice() {
		return price;
	}
	
	@Override
	public TextPanel getInformation() {
		return information;
	}
}