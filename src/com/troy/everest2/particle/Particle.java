package com.troy.everest2.particle;

import java.awt.image.BufferedImage;
import com.troy.everest2.Game;
import com.troy.troyberry.math.Maths;
import com.troy.troyberry.math.Vector2f;

public class Particle {

	public Vector2f position, velocity, size, gravity;
	public BufferedImage image;
	public double age, moveFactor;

	public Particle(Vector2f position, BufferedImage image, Vector2f gravity) {
		this.position = position;
		this.velocity = new Vector2f(0, 0);
		float aspectRatio = (float) Game.frame.getWidth() / Game.frame.getHeight();
		this.size = new Vector2f(0.07f * aspectRatio, 0.07f);
		this.image = image;
		this.gravity = gravity;
		this.age = Maths.randRange(0.0, Math.PI);
		this.moveFactor = ((double) System.nanoTime()) / 1000.0 % 100.0;
	}

}
