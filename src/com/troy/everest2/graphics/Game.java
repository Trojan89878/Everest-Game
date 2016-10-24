package com.troy.everest2.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import com.troy.everest2.gamestate.GameStateManager;
import com.troy.everest2.input.KeyHandler;
import com.troy.everest2.input.Keyboard;
import com.troy.everest2.util.Version;

public class Game extends Canvas {

	private static final int UPDATES_PER_SECOND = 150;

	private static boolean running = false;
	public static GameStateManager gsm;

	public static JFrame frame;

	public Game() {
		setupFrame();
		gsm = new GameStateManager(new TitleScreenPanel());
		gsm.start();
	}

	public void setupFrame() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(1600, 896);
		frame.setTitle(Version.getWindowTitle());
		frame.add(this);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				running = false;
			}
		});
		frame.addKeyListener(new Keyboard());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}

	private void loop() {
		double timePerTick = 1000000000.0 / UPDATES_PER_SECOND;

		long updateTimer = 0;
		int updateCount = 0;

		double delta = 0;
		int updates = 0, missedUpdates;
		long now, lastTime = System.nanoTime();

		while (running) {
			//***** Update Game *****
			now = System.nanoTime();
			long nowMinusLastTime = (now - lastTime);

			delta += nowMinusLastTime / timePerTick;
			updateTimer += nowMinusLastTime;
			lastTime = now;

			while (delta >= 1) {
				KeyHandler.update();
				update(updateCount);
				updateCount++;
				updates++;
				delta--;
			}
			if (updateTimer >= 1000000000) {
				if (updates * 1.05 < (double) UPDATES_PER_SECOND) System.out.println("5% behind on update " + updates + "/" + UPDATES_PER_SECOND);
				updates = 0;
				updateTimer = 0;
			}
			//***** Render Game *****

			BufferStrategy bs = getBufferStrategy();
			while (bs == null) {
				createBufferStrategy(3);
				bs = getBufferStrategy();
			}
			render(bs.getDrawGraphics());
			bs.show();

		}
		System.out.println("Everest Game completed succscfully!");
		System.exit(0);
	}

	public void update(int updateCount) {
		gsm.update(updateCount);
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		gsm.render(g);
		g.dispose();
	}

	public void start() {
		if (running) return;
		running = true;
		loop();
	}

	public void stop() {
		if (!running) return;
		running = false;
	}

}
