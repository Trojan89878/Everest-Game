package com.troy.everest2.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.troy.everest2.Game;
import com.troy.everest2.input.Controls;
import com.troy.everest2.input.KeyBinding;
import com.troy.troyberry.math.Maths;
import com.troy.troyberry.math.Vector2f;

public class Player extends Entity {

	public Player(Vector2f position, BufferedImage image, Vector2f size) {
		super(position, image, size);
	}

	@Override
	public void render(Graphics g) {
		int width = Maths.round(size.x * Game.frame.getHeight()) / 2;
		int height = Maths.round(size.y * Game.frame.getWidth()) / 2;
		g.drawImage(image, Maths.round(position.x), Maths.round(position.y - size.y * Game.frame.getHeight()), width, height, null);
	}

	@Override
	public void update(int updateCount) {
		if (Controls.RIGHT.isPressed()) {
			velocity.x = 0.1f;
		} else if (Controls.LEFT.isPressed()) {
			velocity.x = -0.1f;
		}
		if (Controls.isPressingMoreThenAmount(new KeyBinding[] { Controls.LEFT, Controls.RIGHT }, 1)) {
			velocity.x = 0;
		}
		this.position.add(velocity);
		this.position.y = World.world.getHeight(position.x, true);
	}

}
