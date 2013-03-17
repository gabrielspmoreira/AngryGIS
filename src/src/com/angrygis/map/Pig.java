package com.angrygis.map;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.Symbol;

public class Pig {
	private Point currentPosition;
	private PigStatus pigStatus = PigStatus.Smiling;	
	private int graphicId;
	
	public Pig(Point currentPosition){
		this.currentPosition = currentPosition;
	}
	
	public Point getPosition(){
		return currentPosition;
	}
	
	public void setPosition(Point currentPosition){
		this.currentPosition = currentPosition;
	}
	
	public void toggleSmileLaugh(){
		if (pigStatus == PigStatus.Smiling){
			pigStatus = PigStatus.Laughing;
		}
		else if(pigStatus == PigStatus.Laughing){
			pigStatus = PigStatus.Smiling;
		}
	}
	
	public void setAstonished(){
		pigStatus = PigStatus.Astonished;
	}
	
	public void setDied(){
		pigStatus = PigStatus.Died;
	}
	
	public Graphic getGraphic(Resources resources){
		Symbol pictureSymbol = getSymbol(resources);
		Graphic graphic = new Graphic(currentPosition, pictureSymbol);

		return graphic;
	}
	
	public Symbol getSymbol(Resources resources){
		Drawable drawable = null;
		switch (pigStatus) {
			case Smiling: 
				drawable = resources.getDrawable(R.drawable.pig_smile);
				break;
			case Laughing: 
				drawable = resources.getDrawable(R.drawable.pig_laughing);
				break;
			case Astonished: 
				drawable = resources.getDrawable(R.drawable.pig_astonished);
				break;
			case Died: 
				drawable = resources.getDrawable(R.drawable.pig_died);
				break;
		}
		
		PictureMarkerSymbol pictureSymbol = new PictureMarkerSymbol(drawable);
		return pictureSymbol;
	}
	
	public int getGraphicId(){		
		return graphicId;
	}
	
	public void setGraphicId(int id){		
		graphicId = id;
	}
	
}
