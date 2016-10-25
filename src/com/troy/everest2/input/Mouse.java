package com.troy.everest2.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import com.troy.everest2.Game;
import com.troy.troyberry.math.Maths;
import com.troy.troyberry.math.Vector2f;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private static boolean[] buttons = new boolean[128];
	private static boolean DEBUG = false, inWindow = true;
	private static int x, y;
	private static double mouseWheelRotation = 0;

	public static boolean isButtonDown(int button) {
		if (button < 0 || button > buttons.length - 1) {
			System.err.println("Mouse button not supported!!! Button: " + button);
			return false;
		}
		return buttons[button];
	}

	public Mouse() {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (DEBUG) System.out.println("mouseDragged");
		updatePosition(e);
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (DEBUG) System.out.println("mouseMoved");
		updatePosition(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (DEBUG) System.out.println("clicked ");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (DEBUG) System.out.println("mouseEntered");
		inWindow = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (DEBUG) System.out.println("mouseExited");
		MouseHandler.unPressAllButtons();
		inWindow = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (DEBUG) System.out.println("mousePressed " + e.getButton());
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (DEBUG) System.out.println("mouseReleased " + e.getButton());
		buttons[e.getButton()] = false;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (DEBUG) System.out.println("mouseWheelMoved");
		mouseWheelRotation -= e.getPreciseWheelRotation();
	}

	private void updatePosition(MouseEvent e) {
		x = Maths.clamp(0, Game.frame.getWidth(), e.getX());
		y = Maths.clamp(0, Game.frame.getHeight(), e.getY());
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static Vector2f getPosition() {
		return new Vector2f(x, y);
	}

	public static boolean isInWindow() {
		return inWindow;
	}

	public static double getWheelRotation() {
		return mouseWheelRotation;
	}

}
