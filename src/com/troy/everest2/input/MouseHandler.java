package com.troy.everest2.input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MouseHandler {

	protected static List<MouseBinding> mouseBinds = new ArrayList<MouseBinding>();

	private MouseHandler() {
	}

	/** Called to update the list of keybinds **/
	public static void update() {
		Iterator<MouseBinding> i = mouseBinds.iterator();
		while (i.hasNext()) {
			MouseBinding key = (MouseBinding) i.next();
			if (key.isPressed() && key.lastPress == false) {
				key.pressed = true;
			}
			key.lastPress = key.isPressed();
		}
	}

	/** Resets all keybindings to their default setting **/
	public static void resetAllKeysBindings() {
		Iterator<MouseBinding> i = mouseBinds.iterator();
		while (i.hasNext()) {
			((MouseBinding) i.next()).resetKey();

		}
	}

	/** Sets all keys to be un-pressed **/
	public static void unPressAllButtons() {
		Iterator<MouseBinding> i = mouseBinds.iterator();
		while (i.hasNext()) {
			MouseBinding key = (MouseBinding) i.next();
			key.pressed = false;
			key.lastPress = false;
		}
	}

}
