package com.angrygis.map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;


import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.Layer;
import com.esri.android.map.event.OnLongPressListener;
import com.esri.android.map.event.OnPinchListener;
import com.esri.android.map.event.OnSingleTapListener;
import com.angrygis.map.*;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.*;




public class AngryGISActivity extends Activity {
	
	private MapView map = null;
	private GraphicsLayer birdsLayer = null;
	private GraphicsLayer pigsLayer = null;
	private Timer timer = new Timer();
	private Point lastTarget = null;
	private boolean timing = false; 
	
	private List<Bird> birds = new ArrayList<Bird>(); 
	private List<Pig> pigs = new ArrayList<Pig>(); 
	
	//Dynamic layer URL from ArcGIS online
	private String dynamicMapURL = 
			"http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/Specialty/ESRI_StateCityHighway_USA/MapServer";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Retrieve the map and initial extent from XML layout
		map = (MapView)findViewById(R.id.map);
		ArcGISDynamicMapServiceLayer baseMap = new ArcGISDynamicMapServiceLayer(
		"http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer");
		map.addLayer(baseMap);
		
		//Creates a dynamic layer using service URL 
		ArcGISDynamicMapServiceLayer dynamicLayer = new ArcGISDynamicMapServiceLayer(dynamicMapURL);
		//Adds layer into the 'MapView'
		map.addLayer(dynamicLayer);
		
		pigsLayer = new GraphicsLayer(baseMap.getSpatialReference(), baseMap.getFullExtent());
		map.addLayer(pigsLayer);
		
		birdsLayer = new GraphicsLayer(baseMap.getSpatialReference(), baseMap.getFullExtent());
		map.addLayer(birdsLayer);

		map.setOnSingleTapListener(new OnSingleTapListener() {
					
					
					public void onSingleTap(float x, float y) {
						if (map.isLoaded()) {							
							Random random = new Random();
							int birdId = random.nextInt(BirdType.values().length);
							BirdType birdType = BirdType.values()[birdId];
							Bird bird = BirdFactory.create(birdType);
							Point mapPoint = map.toMapPoint(new Point(x,y));
							bird.setInitialPosition(mapPoint);
							bird.setTarget(lastTarget);
							Graphic birdGraphic = bird.getGraphic(getResources());
							
							birds.add(bird);															
							birdsLayer.addGraphic(birdGraphic);
							
							// Setting the graphic ID in the layer, to allow updating the graphic with more performance 
							int[] graphicIds = birdsLayer.getGraphicIDs();
						    bird.setGraphicId(graphicIds[graphicIds.length-1]);
						}
					}
				});
		
		//Sets 'setOnLongPressListener' to 'MapView'
		map.setOnLongPressListener(new OnLongPressListener() {
				
			public void onLongPress(float x, float y) {
				if (map.isLoaded()) {
					lastTarget = map.toMapPoint(new Point(x,y));
					Pig pig = new Pig(lastTarget);
					pigs.add(pig);
					Graphic graphic = pig.getGraphic(getResources());
					pigsLayer.addGraphic(graphic);
					
					// Setting the graphic ID in the layer, to allow updating the graphic with more performance
					int[] graphicIds = pigsLayer.getGraphicIDs();
					pig.setGraphicId(graphicIds[graphicIds.length-1]);
								
					// Setting the current target of all birds
					int birdsCount = birds.size();		
					for (int i = 0; i < birdsCount; i++) {
						Bird bird = birds.get(i);
						bird.setTarget(lastTarget);
					}
					
					setTimer();
				}
			}
		});
		
		
		/*map.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				Log.i("touch", arg0 + " " + arg1.getAction()); 
				return true; 
			}
		});*/
		
		Toast.makeText(this, "Long press to mark a target.\nPress to throw a bird!",
				Toast.LENGTH_SHORT).show();	
		

    }

	@Override 
	protected void onDestroy() { 
		super.onDestroy();
 }
	@Override
	protected void onPause() {
		super.onPause();
		map.pause();
		
		stopTimer();
 }
	@Override 	
	protected void onResume() {
		super.onResume(); 
		map.unpause();
		
		setTimer();
	}
	
	
	protected void setTimer() {
		if (!timing){
			AnimationTimerTask task = new AnimationTimerTask(birds, pigs, birdsLayer, pigsLayer, getResources());
			timer.schedule(task, 10, 50);
			timing = true;
		}		
	}
	
	protected void stopTimer() {
		timer.cancel();
	}

}



//http://developer.android.com/reference/java/util/concurrent/ScheduledExecutorService.html
//http://www.linuxtopia.org/online_books/programming_books/thinking_in_java/TIJ315_013.htm
//http://tutorials.jenkov.com/java-concurrency/thread-safety.html