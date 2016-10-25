package com.troy.everest2.gamestate;

import java.awt.Graphics;
import com.troy.everest2.Game;
import com.troy.everest2.graphics.Assets;
import com.troy.everest2.world.Player;
import com.troy.everest2.world.World;
import com.troy.troyberry.math.Vector2f;

public class GamePanel implements GameState {

	private Player player;

	@Override
	public void render(Graphics g) {
		World.world.render(g);
	}

	@Override
	public void update(int updateCount) {
		World.world.update(updateCount);
	}

	@Override
	public void onStart() {
		float aspectRatio = (float) Game.frame.getWidth() / Game.frame.getHeight();
		player = new Player(new Vector2f(1207, 1014), Assets.snowBall.getImage(), new Vector2f(0.05f * aspectRatio, 0.05f));
		World.world.add(player);
		for (int i = 1207; i <= 1706; i++) {
			System.out.println(i + " at is " + World.world.getHeight(i, true));
		}

	}

	@Override
	public void onEnd() {
	}

	@Override
	public void postRender(Graphics g) {
	}

}
