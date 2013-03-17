package com.angrygis.map;

public class BirdFactory {
	
	public static Bird create(BirdType birdType){
		switch(birdType){
			case Red:  return new RedBird();
			case Green:  return new GreenBird();
			case White:  return new WhiteBird();
			case Black:  return new BlackBird();
			case Yellow:  return new YellowBird();
		}
		return null;
	}

}
