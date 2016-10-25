package com.troy.everest2.input;

/** This class will contain all of the controls for the game**/
public class Controls {

	/** A keybinding that is used to control movements **/
	public static final KeyBinding NEXT = new KeyBinding(Keyboard.KEY_ENTER), RIGHT = new KeyBinding(Keyboard.KEY_D),
		LEFT = new KeyBinding(Keyboard.KEY_A);
	public static final MouseBinding SELECT = new MouseBinding(1);

	public static void init() {

	}

	public static boolean isPressingMoreThenAmount(KeyBinding[] keyBindings, int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount cant be negative! " + amount);
		}
		int count = 0;
		for (KeyBinding i : keyBindings) {
			if (i.isPressed()) count++;
		}
		return count > amount;
	}

}
