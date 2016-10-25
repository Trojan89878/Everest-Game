package com.troy.everest2.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import com.troy.everest2.input.Controls;
import com.troy.everest2.input.Mouse;
import com.troy.troyberry.math.Maths;

public class UI {

	public int x, y;
	private int width, height, padding;
	private boolean centered;
	int mouseLastX = 0, mouseLastY = 0;

	private List<UIComponent> children;

	public UI(int x, int y, int width, int height, int padding, boolean centered) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.padding = padding;
		this.centered = centered;
		this.children = new ArrayList<UIComponent>();
		this.pack();
	}

	public void pack() {
		updateComponents();
	}

	private void updateComponents() {
		int pointerX = 0, pointerY = padding;
		List<UIComponent> newChildren = new ArrayList<UIComponent>(children.size());
		for (UIComponent thing : children) {
			if (thing.staticPosition) {
				newChildren.add(thing);
				continue;
			}
			pointerX += padding;
			if (children.indexOf(thing) > 0) pointerX += thing.width;
			if (pointerX + thing.width > this.width) {
				pointerX = padding;
				pointerY += thing.height + padding;
			}
			thing.x = pointerX;
			thing.y = pointerY;
			newChildren.add(thing);
		}
		this.children = newChildren;
	}

	public void update(int updateCount) {
		int mouseX = Mouse.getX(), mouseY = Mouse.getY();
		for (UIComponent c : children) {
			c.update(updateCount);
			boolean mouseOver = Maths.inRange(mouseX, c.x, c.x + c.width) && Maths.inRange(mouseY, c.y, c.y + c.height);
			if (mouseOver) {
				if (!c.hasHovered) {
					c.hasHovered = true;
					c.onHover(mouseX, mouseY);
				}
				if (Controls.SELECT.hasBeenPressed()) {
					c.onClick(mouseX, mouseY);
				}
			} else {
				if (c.hasHovered) c.offHover(mouseX, mouseY);
				c.hasHovered = false;
			}
		}
	}

	public void render(Graphics g) {
		for (UIComponent c : children) {
			c.render(g);
		}
	}

	public void add(UIComponent child) {
		children.add(child);
	}

	public void remove(UIComponent child) {
		children.remove(child);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
