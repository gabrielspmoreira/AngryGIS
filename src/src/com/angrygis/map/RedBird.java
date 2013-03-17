package com.angrygis.map;

public class RedBird extends Bird {

	@Override
	protected int getSpeed() {
		return 100000;
	}

	@Override
	protected int getBirdDrawableId(int directionWE) {
		if (directionWE == -1){
			return R.drawable.redbird_left;
		}
		else{
			return R.drawable.redbird_right;
		}
			
	}

	@Override
	protected float getRotateBy(){
		return 0;
	}
}
