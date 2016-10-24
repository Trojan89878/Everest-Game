package com.troy.everest2.graphics;

import java.awt.Color;
import java.awt.Graphics;
import com.troy.everest2.gamestate.GameState;

public class GamePanel implements GameState {

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(100, 100, 500, 500);
	}

	@Override
	public void update(int updateCount) {
	}

	@Override
	public void onStart() {

	}

	@Override
	public void onEnd() {
	}

}
