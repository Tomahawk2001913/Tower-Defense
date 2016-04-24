package com.tomahawk2001913.landscrapetoo.towerdefense.map.towers;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.Text;
import com.tomahawk2001913.landscrapetoo.towerdefense.ui.TextPanel;

public enum LaserCannonUpgrades implements TowerUpgrades {
	ORIGINAL(100, 0.5f, 500, "No upgrades for this beast of a tower!");
	
	private float damage, fireRate;
	private int price;
	private TextPanel information;
	
	private LaserCannonUpgrades(float damage, float fireRate, int price, String name) {
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
	public int getPrice() {
		return price;
	}
	
	@Override
	public TextPanel getInformation() {
		return information;
	}
}