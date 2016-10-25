package com.troy.everest2.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.troy.troyberry.math.Vector2f;

public abstract class Entity {

	protected BufferedImage image;
	protected Vector2f position, velocity, size;

	public Entity(Vector2f position, BufferedImage image, Vector2f size) {
		this.position = position;
		this.velocity = new Vector2f();
		this.image = image;
		this.size = size;
	}

	public abstract void render(Graphics g);

	public abstract void update(int updateCount);

}
