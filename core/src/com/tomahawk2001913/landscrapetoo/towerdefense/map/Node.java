package com.tomahawk2001913.landscrapetoo.towerdefense.map;

import com.badlogic.gdx.math.Vector2;

public class Node {
	private Node parent;
	private Vector2 location;
	
	private float gCost, hCost, fCost;
	
	public Node(Vector2 location, Node parent, Vector2 goal) {
		this.location = location;
		
		hCost = (float) TileMap.getDistance(location, goal);
		
		setParent(parent);
	}
	
	public Node getParent() {
		return parent;
	}
	
	public Vector2 getLocation() {
		return location;
	}
	
	public float getGCost() {
		return gCost;
	}
	
	public float getHCost() {
		return hCost;
	}
	
	public float getFCost() {
		return fCost;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
		
		if(parent != null) {
			gCost = (float) TileMap.getDistance(location, parent.getLocation());
			gCost += parent.getGCost();
		} else gCost = 0;
		
		fCost = gCost + hCost;
	}
	
	public void setLocation(Vector2 location) {
		this.location = location;
	}
}