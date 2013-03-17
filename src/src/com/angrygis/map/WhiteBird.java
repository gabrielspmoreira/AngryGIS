package com.angrygis.map;

public class WhiteBird extends Bird {

	@Override
	protected int getSpeed() {
		return 300000;
	}

	@Override
	protected int getBirdDrawableId(int directionWE) {
		if (directionWE == -1){
			return R.drawable.whitebird_left;
		}
		else{
			return R.drawable.whitebird_right;
		}
			
	}
	
	@Override
	protected float getRotateBy(){
		return 0;
	}

}
