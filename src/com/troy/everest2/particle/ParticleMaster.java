package com.troy.everest2.particle;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import com.troy.everest2.Game;
import com.troy.troyberry.math.Maths;

public class ParticleMaster {

	private static List<Particle> particles = new ArrayList<Particle>();

	private ParticleMaster() {
	}

	public static void update() {
		for (Particle p : particles) {
			p.age += 0.01;
			p.position.x += (float) (Math.sin(p.age) / (1000.0 - p.moveFactor));
			p.position.add(p.velocity);
			p.position.add(p.gravity);
			if (p.position.y > 1) {
				p.position.y = -1 - p.size.y * 2;
			}
		}
	}

	public static void render(Graphics g) {
		for (Particle p : particles) {
			int x = Maths.round((p.position.x + 1f) * (Game.frame.getWidth() / 2f));
			int y = Maths.round((p.position.y + 1f) * (Game.frame.getHeight() / 2f));
			int width = Maths.round(p.size.x * Game.frame.getHeight()) / 2;
			int height = Maths.round(p.size.y * Game.frame.getWidth()) / 2;
			g.drawImage(p.image, x, y, width, height, null);
		}
	}

	public static void addParticle(Particle p) {
		particles.add(p);

	}

	public static void clear() {
		particles.clear();
	}

}
