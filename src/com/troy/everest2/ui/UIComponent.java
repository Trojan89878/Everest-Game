package com.troy.everest2.ui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class UIComponent {

	protected int x, y, width, height;
	protected boolean staticPosition = false, hasHovered = false;

	private ClickListener clickListener = null;
	private HoverListener hoverListener = null;

	public UIComponent(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void setPosition(int x, int y) {
		staticPosition = true;
		this.x = x;
		this.y = y;
	}

	public void drawBoundingBox(Graphics g, int color) {
		g.setColor(new Color(color));
		g.drawRect(x, y, width, height);
	}

	public void fillBoundingBox(Graphics g, int color) {
		g.setColor(new Color(color));
		g.fillRect(x, y, width, height);
	}

	public abstract void update(int updateCount);

	public abstract void render(Graphics g);

	public abstract void onHover(int x, int y);

	public void callOnClick() {
		if (clickListener != null) clickListener.onClick();
	}

	public void callOnHover() {
		if (hoverListener != null) hoverListener.onHover();
	}

	public void callOffHover() {
		if (hoverListener != null) hoverListener.offHover();
	}

	public void setHoverListener(HoverListener l) {
		hoverListener = l;
	}

	public void setClickListener(ClickListener l) {
		clickListener = l;
	}

	public abstract void offHover(int x, int y);

	public abstract void onClick(int x, int y);

	public boolean equals(Object other) {
		if (!(other instanceof UIComponent)) return false;
		UIComponent c = (UIComponent) other;
		if (c.x == this.x && c.y == this.y && c.width == this.width && c.height == this.height && c.staticPosition == this.staticPosition) {
			return true;
		}
		return false;
	}

}
