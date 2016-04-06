package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import java.util.HashMap;

import com.tomahawk2001913.landscrapetoo.towerdefense.map.entities.Entity;

public enum Waves {
	ONE(new HashMap<Entity, Integer>());
	
	private HashMap<Entity, Integer> entities;
	
	private Waves(HashMap<Entity, Integer> entities) {
		this.entities = entities;
	}
	
	public HashMap<Entity, Integer> getEntities() {
		return entities;
	}
}