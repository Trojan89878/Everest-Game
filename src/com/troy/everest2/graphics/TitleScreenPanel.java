package com.troy.everest2.graphics;

import java.awt.Graphics;
import com.troy.everest2.gamestate.GameState;
import com.troy.everest2.input.Controls;

public class TitleScreenPanel implements GameState {

	@Override
	public void render(Graphics g) {

	}

	@Override
	public void update(int updateCount) {
		if (Controls.NEXT.hasBeenPressed()) {
			Game.gsm.setState(new GamePanel());
		}
	}

	@Override
	public void onStart() {

	}

	@Override
	public void onEnd() {
	}

}
