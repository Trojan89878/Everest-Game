package com.troy.everest2.gamestate;

import java.awt.Graphics;

public class GameStateManager {

	private GameState currentState;

	public GameStateManager(GameState currentState) {
		this.currentState = currentState;
	}

	public void setState(GameState newState) {
		currentState.onEnd();
		currentState = newState;
		newState.onStart();
	}

	public void update(int updateCount) {
		currentState.update(updateCount);
	}

	public GameState getCurrentState() {
		return currentState;
	}

	public void render(Graphics g) {
		currentState.render(g);
	}

	public void start() {
		currentState.onStart();
	}

	public void end() {
		currentState.onEnd();
	}

}
