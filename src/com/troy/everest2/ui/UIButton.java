package com.troy.everest2.ui;

import java.awt.Color;
import java.awt.Graphics;
import com.troy.everest2.Game;
import com.troy.troyberry.javagraphics.GraphicsUtil;
import com.troy.troyberry.utils.ColorUtil;

public class UIButton extends UIComponent {

	public String label = "";
	private boolean hasImage = false;
	private int color;
	private final int origionalColor;
	private final boolean hasColor;
	private double divideFactor;

	public UIButton(int width, int height, String label, int color, double divideFactor) {
		super(0, 0, width, height);
		this.label = label;
		hasColor = true;
		this.origionalColor = color;
		this.color = color;
		this.divideFactor = divideFactor;

	}

	@Override
	public void update(int updateCount) {
	}

	@Override
	public void render(Graphics g) {
		super.fillBoundingBox(g, color);
		g.setColor(Color.black);
		GraphicsUtil.drawCenteredString(g, label, x + width / 2, y + height / 2, Game.gameFont);
	}

	@Override
	public void onHover(int x, int y) {
		super.callOnHover();
		if (hasColor) color = ColorUtil.divide(color, divideFactor);
	}

	@Override
	public void offHover(int x, int y) {
		super.callOffHover();
		if (hasColor) color = origionalColor;
	}

	@Override
	public void onClick(int x, int y) {
		super.callOnClick();
	}

}
