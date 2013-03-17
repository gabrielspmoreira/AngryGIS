package com.angrygis.map;

import com.esri.core.geometry.Point;
import com.esri.core.symbol.*;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.esri.core.map.Graphic;

public abstract class Bird {
	
	protected int speed;
	protected BirdDirection direction;
	protected PictureMarkerSymbol symbol;
	protected BirdDirection birdDirection;
	protected int graphicId;
	protected float currentAngle;
	
	public Bird(){
		int speed = getSpeed();
		this.birdDirection = new BirdDirection(speed);	
	}
	
	protected abstract int getSpeed();	
	
	protected abstract int getBirdDrawableId(int directionWE);
	
	protected abstract float getRotateBy();
	
	public void setInitialPosition(Point initialPosition){
		birdDirection.setInitialPosition(initialPosition);
	}
	
	public void setTarget(Point target){
		birdDirection.setTarget(target);
	}
	
	public void move(){
		birdDirection.move();			
	}
	
	public Graphic getGraphic(Resources resources){
		Symbol symbol = getSymbol(resources);
		
		Point currentPosition = birdDirection.getCurrentPosition();
		Graphic graphic = new Graphic(currentPosition, symbol);
		
		return graphic;
	}
	
	public PictureMarkerSymbol getSymbol(Resources resources){
		Drawable drawable = resources.getDrawable(getBirdDrawableId(birdDirection.getDirectionWE()));		
		PictureMarkerSymbol pictureSymbol = new PictureMarkerSymbol(drawable);
		
		currentAngle = (currentAngle - getRotateBy()) % 360;
		pictureSymbol.setAngle(currentAngle);
		
		return pictureSymbol;
	}
	
	public Point getCurrentPosition(){
		return birdDirection.getCurrentPosition();
	}
	
	public int getGraphicId(){		
		return graphicId;
	}
	
	public void setGraphicId(int id){		
		graphicId = id;
	}
}


