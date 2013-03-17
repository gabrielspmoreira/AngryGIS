package com.angrygis.map;

import com.esri.core.geometry.Point;

public class BirdDirection {
	
	private Point target;
	private Point currentPosition;
	private float angle;
	private int speed;
	private int directionWE;
	private int directionNS;
	
	public BirdDirection(int speed) {		
		this.speed = speed;
	}
	
	public Point getTarget(){
		return target;
	}
	
	public void setTarget(Point target){
		this.target = target;
		
		if (target == null){
			return;
		}
			
		if (target.getX() < currentPosition.getX()){
			directionWE = -1;
		}
		else {
			directionWE = 1;
		}
		
		if (target.getY() < currentPosition.getY()){
			directionNS = -1;
		}
		else {
			directionNS = 1;
		}
	}
	
	public float getAngle(){
		return angle;
	}
	
	public int getDirectionWE(){
		return directionWE;
	}
	
	public int getDirectionNS(){
		return directionNS;
	}
	
	public Point getCurrentPosition(){
		return currentPosition;
	}
	
	public void setInitialPosition(Point initialPosition){
		this.currentPosition = initialPosition;
	}
	
	public void move(){
		if (target == null || currentPosition == null)
			return;
		
		double x = currentPosition.getX();
		double y = currentPosition.getY();
		double targetX = target.getX();
		double targetY = target.getY();
		double deltaX = (targetX - x);
		double deltaY = (targetY - y);
				
		double a = deltaY / deltaX;
		double b = y - (deltaY / deltaX * x);
		
		double hipotenusa = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		double cosine = deltaX / hipotenusa;		
		
		double newX = x + (speed * Math.abs(cosine) * (deltaX > 0? 1 : -1));
		double newY = a*newX+b;
		
		this.currentPosition = new Point(newX, newY);
		this.angle = (float) Math.acos(cosine);
	}
}
