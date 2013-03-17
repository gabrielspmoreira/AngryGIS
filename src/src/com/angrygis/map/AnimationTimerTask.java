package com.angrygis.map;

import java.util.List;
import java.util.TimerTask;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.esri.android.map.GraphicsLayer;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;

public class AnimationTimerTask extends TimerTask {
		
	private Resources resources;
	private GraphicsLayer birdsLayer;
	private GraphicsLayer pigsLayer;
	private List<Bird> birds;
	private List<Pig> pigs;
	
	public AnimationTimerTask(List<Bird> birds, List<Pig> pigs, GraphicsLayer birdsLayer, GraphicsLayer pigsLayer,Resources resources){
		this.birdsLayer = birdsLayer;
		this.pigsLayer = pigsLayer;
		this.birds = birds;
		this.pigs = pigs;
		this.resources = resources;
	}
	
	public void run() {
		
		int birdsCount = birds.size();		
		for (int i = 0; i < birdsCount; i++) {
			Bird bird = birds.get(i);
			bird.move();
			int id = bird.getGraphicId();
			PictureMarkerSymbol symbol = bird.getSymbol(resources);
			birdsLayer.updateGraphic(id, bird.getCurrentPosition());
			birdsLayer.updateGraphic(id, symbol);
		}		
		
		int pigsCount = pigs.size();		
		for (int i = 0; i < pigsCount; i++) {
			Pig pig = pigs.get(i);
			pig.toggleSmileLaugh();	
			int id = pig.getGraphicId();
			pigsLayer.updateGraphic(id, pig.getSymbol(resources));
		}
	}
		
}
