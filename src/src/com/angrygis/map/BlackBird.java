package com.angrygis.map;

public class BlackBird extends Bird {

	@Override
	protected int getSpeed() {
		return 50000;
	}

	@Override
	protected int getBirdDrawableId(int directionWE) {
		if (directionWE == -1){
			return R.drawable.blackbird_left;
		}
		else{
			return R.drawable.blackbird_right;
		}
			
	}

	@Override
	protected float getRotateBy(){
		return 0;
	}
}
