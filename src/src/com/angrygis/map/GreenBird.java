package com.angrygis.map;

public class GreenBird extends Bird {

	@Override
	protected int getSpeed() {
		return 20000;
	}

	@Override
	protected int getBirdDrawableId(int directionWE) {
		if (directionWE == -1){
			return R.drawable.greenbird_left;
		}
		else{
			return R.drawable.greenbird_right;
		}
			
	}
	
	@Override
	protected float getRotateBy(){
		return 30;
	}

}
