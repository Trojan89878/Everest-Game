package com.troy.everest2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import com.troy.everest2.gamestate.GameStateManager;
import com.troy.everest2.gamestate.TitleScreenPanel;
import com.troy.everest2.graphics.Assets;
import com.troy.everest2.input.KeyHandler;
import com.troy.everest2.input.Keyboard;
import com.troy.everest2.input.Mouse;
import com.troy.everest2.input.MouseHandler;
import com.troy.everest2.particle.Particle;
import com.troy.everest2.particle.ParticleMaster;
import com.troy.everest2.util.Version;
import com.troy.troyberry.math.Maths;
import com.troy.troyberry.math.Vector2f;

public class Game extends Canvas {

	private static final int UPDATES_PER_SECOND = 150;

	public static boolean running = false;
	public static GameStateManager gsm;
	public static Font gameFont = new Font("Vendana", Font.BOLD, 27), bigGameFont = new Font(gameFont.getName(), Font.PLAIN, 60);

	public static JFrame frame;

	public Game() {
		setupFrame();
		Assets.load();

		gsm = new GameStateManager(new TitleScreenPanel());
		gsm.start();
		for (int i = 0; i < 100; i++) {
			ParticleMaster.addParticle(new Particle(new Vector2f(Maths.randRange(-1.25f, 1.25f), Maths.randRange(-1, 1f)), Assets.snowBall.getImage(),
				new Vector2f(0, 0.0009f)));
		}
	}

	public void update(int updateCount) {
		gsm.update(updateCount);
		MouseHandler.update();
		ParticleMaster.update();
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(gameFont);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setColor(Color.black);
		gsm.render(g);
		ParticleMaster.render(g);
		gsm.postRender(g);
		g.dispose();
	}

	private void loop() {
		frame.setVisible(true);
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

	public void start() {
		if (running) return;
		running = true;
		loop();
	}

	public void stop() {
		running = false;
	}

	public void setupFrame() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setVisible(false);
		frame.setTitle(Version.getWindowTitle());
		frame.add(this);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				running = false;
			}
		});
		Mouse mouse = new Mouse();
		this.addKeyListener(new Keyboard());
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.addMouseWheelListener(mouse);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		DisplayMode g = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
		frame.setSize(g.getWidth(), g.getHeight());
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		if (Main.DEBUG) frame.setLocation((1920 - frame.getWidth()) / 2, 0);
		else frame.setLocationRelativeTo(null);
		try {
			frame.setIconImage(ImageIO.read(Class.class.getResourceAsStream("/textures/Everestlogo.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
