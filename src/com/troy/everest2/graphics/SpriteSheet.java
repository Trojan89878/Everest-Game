package com.troy.everest2.graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage image;

	public SpriteSheet(String path) {
		path = "/textures/" + path + ".png";
		try {
			image = ImageIO.read(Class.class.getResourceAsStream(path));
		} catch (Exception e) {
			System.err.println("Unable to load sprite sheet " + path);
		}
	}

	public BufferedImage getSubImage(int x, int y, int width, int height) {
		return image.getSubimage(x, y, width, height);
	}

}
