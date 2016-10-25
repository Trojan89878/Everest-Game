package com.troy.everest2.graphics;

public class Assets {

	public static Texture climber, snowBall, everest;

	public static void load() {
		climber = new Texture("climberstanding");
		snowBall = new Texture("snowball");
		everest = new Texture("Everest");
	}

	private Assets() {
	}

}
