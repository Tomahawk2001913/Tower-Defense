package com.tomahawk2001913.landscrapetoo.towerdefense.ui;

import com.tomahawk2001913.landscrapetoo.towerdefense.gamestates.Playing;
import com.tomahawk2001913.landscrapetoo.towerdefense.io.AssetHandler;
import com.tomahawk2001913.landscrapetoo.towerdefense.map.towers.Tower;

public class UpgradeTowerButton extends Button {
	private Tower tower;
	
	public UpgradeTowerButton(float x, float y, float width, float height, boolean show, Tower tower) {
		super(x, y, width, height, show, AssetHandler.dirtTile);
		this.tower = tower;
	}

	@Override
	public void action() {
		if(Playing.hasMoney(tower.getUpgrade().getPrice())) {
			Playing.subtractMoney(tower.getUpgrade().getPrice());
			tower.upgrade();
		}
	}

}
