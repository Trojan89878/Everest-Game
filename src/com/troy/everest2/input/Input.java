package com.troy.everest2.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements MouseListener, MouseMotionListener, KeyListener {

	public static int mouseX, mouseY;

	public void keyPressed(KeyEvent e) {
		Keyboard.setKey(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {
		Keyboard.setKey(e.getKeyCode(), false);
	}

	public void mouseClicked(MouseEvent e) {

	}

	public static String getKeyName(int keyId) {
		return KeyEvent.getKeyText(keyId);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
}
