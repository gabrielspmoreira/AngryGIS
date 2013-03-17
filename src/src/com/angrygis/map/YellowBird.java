package com.angrygis.map;

public class YellowBird extends Bird {

	@Override
	protected int getSpeed() {
		return 200000;
	}

	@Override
	protected int getBirdDrawableId(int directionWE) {
		if (directionWE == -1){
			return R.drawable.yellowbird_left;
		}
		else{
			return R.drawable.yellowbird_right;
		}
			
	}
	
	@Override
	protected float getRotateBy(){
		return 0;
	}

}
