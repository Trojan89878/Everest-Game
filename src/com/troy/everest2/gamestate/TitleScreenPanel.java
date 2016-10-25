package com.troy.everest2.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import com.troy.everest2.Game;
import com.troy.everest2.graphics.Assets;
import com.troy.everest2.input.Controls;
import com.troy.everest2.particle.ParticleMaster;
import com.troy.everest2.ui.ClickListener;
import com.troy.everest2.ui.UI;
import com.troy.everest2.ui.UIButton;
import com.troy.everest2.util.Version;
import com.troy.troyberry.javagraphics.GraphicsUtil;

public class TitleScreenPanel implements GameState {

	private UI ui;

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.everest.getImage(), 0, 0, Game.frame.getWidth(), Game.frame.getHeight(), null);
	}

	@Override
	public void postRender(Graphics g) {
		g.setColor(Color.black);
		GraphicsUtil.drawCenteredString(g, Version.getName(), Game.frame.getWidth() / 2, 100, Game.bigGameFont);
		ui.render(g);
	}

	@Override
	public void update(int updateCount) {
		ui.update(updateCount);
		if (Controls.NEXT.hasBeenPressed()) {
			Game.gsm.setState(new GamePanel());
		}
	}

	@Override
	public void onEnd() {
		ParticleMaster.clear();
	}

	@Override
	public void onStart() {
		ui = new UI(0, 0, Game.frame.getWidth(), Game.frame.getHeight(), 5, false);

		UIButton play = new UIButton(350, 110, "Play", 0x00ffff, 1.09);
		play.setPosition(Game.frame.getWidth() / 2 - 350 / 2, Game.frame.getHeight() / 2 - 200);
		play.setClickListener(new ClickListener() {

			@Override
			public void onClick() {
				Game.gsm.setState(new GamePanel());
			}

		});
		ui.add(play);

		UIButton controls = new UIButton(350, 110, "Options", 0x00ffff, 1.09);
		controls.setPosition(Game.frame.getWidth() / 2 - 350 / 2, Game.frame.getHeight() / 2 - 80);
		controls.setClickListener(new ClickListener() {

			@Override
			public void onClick() {
				System.out.println("This is a wip");
			}

		});
		ui.add(controls);

		UIButton quit = new UIButton(350, 110, "Quit", 0x00ffff, 1.09);
		quit.setPosition(Game.frame.getWidth() / 2 - 350 / 2, Game.frame.getHeight() / 2 - 80 + 120);
		quit.setClickListener(new ClickListener() {

			@Override
			public void onClick() {
				Game.running = false;
			}

		});
		ui.add(quit);

		ui.pack();
	}

}
