package com.troy.everest2.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.troy.everest2.Game;
import com.troy.troyberry.math.Maths;
import com.troy.troyberry.math.Vector2f;

public class Snowball extends Entity {

	public Snowball(Vector2f position, BufferedImage image, Vector2f size) {
		super(position, image, size);
	}

	@Override
	public void render(Graphics g) {
		int width = Maths.round(size.x * Game.frame.getHeight()) / 2;
		int height = Maths.round(size.y * Game.frame.getWidth()) / 2;
		g.drawImage(image, Maths.round(position.x), Maths.round(position.y), width, height, null);
	}

	@Override
	public void update(int updateCount) {
		this.position.add(velocity);
	}

}
