package com.troy.everest2.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import com.troy.everest2.Game;
import com.troy.everest2.GameSettings;
import com.troy.everest2.graphics.Assets;
import com.troy.troyberry.math.Maths;
import com.troy.troyberry.math.Vector2f;

public class World {

	public static World world = new World();

	private List<Entity> entities;
	private Vector2f[] positions = new Vector2f[] { new Vector2f(1207, 1014), new Vector2f(1257, 1015), new Vector2f(1279, 976),
		new Vector2f(1358, 898), new Vector2f(1454, 873), new Vector2f(1477, 862), new Vector2f(1503, 821), new Vector2f(1611, 757),
		new Vector2f(1706, 701), new Vector2f(1524, 509), new Vector2f(1421, 418), new Vector2f(1280, 325), new Vector2f(1261, 297),
		new Vector2f(1214, 313), new Vector2f(1063, 257), new Vector2f(973, 147), new Vector2f(862, 64), new Vector2f(822, 60) };

	public World() {
		entities = new ArrayList<Entity>();
	}

	public void render(Graphics g) {
		g.drawImage(Assets.everest.getImage(), 0, 0, Game.frame.getWidth(), Game.frame.getHeight(), null);

		if (GameSettings.showLines) {
			for (int i = 1; i < positions.length - 1; i++) {
				Vector2f one = positions[i - 1];
				Vector2f two = positions[i];
				g.drawLine(Maths.round(one.x), Maths.round(one.y), Maths.round(two.x), Maths.round(two.y));
			}
		}

		for (Entity e : entities) {
			e.render(g);
		}
	}

	public void update(int updateCount) {
		for (Entity e : entities) {
			e.update(updateCount);
		}
	}

	public int getHeight(float x, boolean bottom) {

		for (int i = 1; i < 9; i++) {
			Vector2f one = positions[i - 1];
			Vector2f two = positions[i];
			if (one.x > two.x) {
				float temp = one.x;
				one.x = two.x;
				two.x = temp;
			}
			if (Maths.inRange(x, one.x, two.x)) {
				return Maths.round(Maths.lerp(one.y, two.y, (x - one.x) / (two.x - one.x)));
			}
		}

		return 1014;
	}

	public void add(Entity e) {
		entities.add(e);
	}

}
