package com.troy.everest2.gamestate;

import java.awt.Graphics;

public interface GameState {

	public void render(Graphics g);

	public void update(int updateCount);

	public void onStart();

	public void onEnd();

}
