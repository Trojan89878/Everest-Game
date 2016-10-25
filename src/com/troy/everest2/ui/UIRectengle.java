package com.troy.everest2.ui;

import java.awt.Graphics;

public class UIRectengle extends UIComponent {

	private int color;

	public UIRectengle(int width, int height, int color) {
		super(0, 0, width, height);
		this.color = color;
	}

	public UIRectengle(int width, int height) {
		this(width, height, 0xff0000);
	}

	@Override
	public void update(int updateCount) {
	}

	@Override
	public void render(Graphics g) {
		super.fillBoundingBox(g, color);
	}

	@Override
	public void onHover(int x, int y) {
		super.callOnHover();
	}

	@Override
	public void offHover(int x, int y) {
		super.callOffHover();
	}

	@Override
	public void onClick(int x, int y) {
		super.callOnClick();
	}

}
