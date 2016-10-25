package com.troy.everest2.graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Texture {

	private BufferedImage image;

	public Texture(String path) {
		path = "/textures/" + path + ".png";
		try {
			image = ImageIO.read(Class.class.getResource(path));
		} catch (Exception e) {
			System.err.println("Unable to load texture " + path);
		}
	}

	public Texture(SpriteSheet spriteSheet, int x, int y, int width, int height) {
		image = spriteSheet.getSubImage(x, y, width, height);
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

}
